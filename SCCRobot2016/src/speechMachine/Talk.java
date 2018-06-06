//package speechMachine;
//import java.util.Arrays;
//import java.util.List;
//
//import marytts.signalproc.effects.JetPilotEffect;
//import marytts.signalproc.effects.LpcWhisperiserEffect;
//import marytts.signalproc.effects.RobotiserEffect;
//import marytts.signalproc.effects.StadiumEffect;
//import marytts.signalproc.effects.VocalTractLinearScalerEffect;
//import marytts.signalproc.effects.VolumeEffect;
//
//public class Talk {
//	/**
//	 * The main method from which our application is starting
//	 * 
//	 * @param args
//	 */
//	public static void main(String[] args) {
//		//Create TextToSpeech
//		TextToSpeech tts = new TextToSpeech();
//		
//		// Setting the Current Voice
//		tts.setVoice("cmu-rms-hsmm");
//		
//		//VocalTractLinearScalerEffect
////		VocalTractLinearScalerEffect vocalTractLSE = new VocalTractLinearScalerEffect(); //russian drunk effect
////		vocalTractLSE.setParams("amount:70");
//		
//		//JetPilotEffect
////		JetPilotEffect jetPilotEffect = new JetPilotEffect(); //epic fun!!!
////		jetPilotEffect.setParams("amount:100");
//		
//		//RobotiserEffect
//		RobotiserEffect robotiserEffect = new RobotiserEffect();
//		robotiserEffect.setParams("amount:50");
//		
//		//StadiumEffect
////		StadiumEffect stadiumEffect = new StadiumEffect();
////		stadiumEffect.setParams("amount:150");
//		
//		//LpcWhisperiserEffect
////		LpcWhisperiserEffect lpcWhisperiserEffect = new LpcWhisperiserEffect(); //creepy
////		lpcWhisperiserEffect.setParams("amount:70");
////		
//		//VolumeEffect
//		VolumeEffect volumeEffect = new VolumeEffect(); //be careful with this i almost got heart attack
//		volumeEffect.setParams("amount:10");
//		
//		//Apply the effects
//		//----You can add multiple effects by using the method `getFullEffectAsString()` and + symbol to connect with the other effect that you want
//		//----check the example below
//		//tts.getMarytts().setAudioEffects(stadiumEffect.getFullEffectAsString());// + "+" + stadiumEffect.getFullEffectAsString());
//		
//		//=========================================================================
//		//===================== Now let's troll user ==============================
//		//=========================================================================
//		tts.speak("en abled", 1.0f, true, true);
//		
//	}
//}
