package robot.utilities;

import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import org.opencv.core.Mat;
import org.opencv.core.Point;
import org.opencv.core.Rect;
import org.opencv.core.Scalar;
import org.opencv.imgproc.Imgproc;

import edu.wpi.cscore.CvSink;
import edu.wpi.cscore.CvSource;
import edu.wpi.first.wpilibj.CameraServer;
import robot.utilities.GripPipeline.*;

public class VisionProcessor {
	// target height in pixels, 1m away
	private static final double HEIGHT_REF_PX = 95.0;

	// current height of the target rectangle in pixels
	private double heightPx;

	// ScheduledExecutorService executor = new ScheduledThreadPoolExecutor(2);
	Timer processingTimer = new Timer();
	Timer outputTimer = new Timer();

	Mat rawFrame = new Mat();
	Mat tempFrame = new Mat();
	Mat processedFrame = new Mat();
	boolean processedFrameReady = false;

	public void start() {
		CvSink input = CameraServer.getInstance().getVideo();
		CvSource output = CameraServer.getInstance().putVideo("Processed Video", 640, 480);
		GripPipeline pipeline = new GripPipeline();
		
		// Process a frame.
		// executor.scheduleWithFixedDelay(() -> {
		processingTimer.schedule(new TimerTask() {
			@Override
			public void run() {
				synchronized (rawFrame) {
					if (rawFrame.empty()) {
						return;
					}
					rawFrame.copyTo(tempFrame);
				}

				pipeline.process(tempFrame);
				output.putFrame(pipeline.hsvThresholdOutput());
				Rect maxRect = new Rect(0, 0, 0, 0);
//				for (Line line : pipeline.filterLinesOutput()) {
//					Imgproc.line(tempFrame, new Point(line.x1, line.y1), new Point(line.x2, line.y2),
//							new Scalar(0, 0, 255));
//				}
				heightPx = maxRect.height;
				
				synchronized (rawFrame) {
					output.putFrame(tempFrame);
					output.putFrame(pipeline.hsvThresholdOutput());
				}

				synchronized (processedFrame) {
					tempFrame.copyTo(processedFrame);
					processedFrameReady = true;
				}
			}
		}, 0, 50);
 
		// Output the latest frame.
		// executor.scheduleWithFixedDelay(() -> {
		processingTimer.schedule(new TimerTask() {
			@Override
			public void run() {
				synchronized (rawFrame) {
					input.grabFrame(rawFrame);
					output.putFrame(rawFrame);
				}
				
				if (processedFrameReady) {
					synchronized (processedFrame) {
						System.out.println(System.currentTimeMillis() + ":Output processed frame");
						output.putFrame(processedFrame);
						processedFrameReady = false;
					}
				} else {
					System.out.println(System.currentTimeMillis() + ": Outputraw frame");
					input.grabFrame(rawFrame);
					output.putFrame(rawFrame);
				}
			}
		}, 0, 50);
	}

	/**
	 * @return distance to the target in meters
	 */
	public double getDistanceMeters() {
		return HEIGHT_REF_PX / heightPx;
	}

	public double getDistanceFeet() {
		return getDistanceMeters() * 3.28;
	}

}
