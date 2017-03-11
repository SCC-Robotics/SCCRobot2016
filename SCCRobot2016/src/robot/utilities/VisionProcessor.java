package robot.utilities;

import java.util.Timer;
import java.util.TimerTask;

import org.opencv.core.Mat;

import edu.wpi.cscore.CvSink;
import edu.wpi.cscore.CvSource;
import edu.wpi.first.wpilibj.CameraServer;
import edu.wpi.first.wpilibj.vision.VisionPipeline;

public class VisionProcessor {

	private Timer timer = new Timer();

	public void start() {
		CvSink input = CameraServer.getInstance().getVideo();
		CvSource output = CameraServer.getInstance().putVideo("Processed Video", 640, 360);
		Mat mat = new Mat();
		GripPipeline pipeline = new GripPipeline();
		
		timer.schedule(new TimerTask() {
			@Override
			public void run() {
				input.grabFrame(mat);
				pipeline.process(mat);
				output.putFrame(pipeline.hslThresholdOutput());
			}
		}, 0, 50);
	}

}
