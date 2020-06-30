package mario;

import java.io.File;
import java.net.MalformedURLException;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.FloatControl;
import javax.sound.sampled.SourceDataLine;
import mario.Start;

public class Music {

	public static void playBackground() {
		try {
			AudioInputStream ais = AudioSystem
					.getAudioInputStream(new File("D:\\E work\\MarioAdventrue\\src\\snd\\background.wav"));
			AudioFormat aif = ais.getFormat();
			final SourceDataLine sdl;
			DataLine.Info info = new DataLine.Info(SourceDataLine.class, aif);
			sdl = (SourceDataLine) AudioSystem.getLine(info);
			sdl.open(aif);
			sdl.start();
			FloatControl fc = (FloatControl) sdl.getControl(FloatControl.Type.MASTER_GAIN);
			int nByte = 0;
			final int SIZE = 1024 * 64;
			byte[] buffer = new byte[SIZE];
			while (nByte != -1) {
				nByte = ais.read(buffer, 0, SIZE);
				sdl.write(buffer, 0, nByte);
			}
			sdl.stop();
		} catch (Exception e) {
			// TODO: handle exception

			e.printStackTrace();
		}

	}

	/**
	 * Ã¯‘æ“Ù
	 */
	public static void playJump() {
		try {
			AudioInputStream ais = AudioSystem
					.getAudioInputStream(new File("D:\\E work\\MarioAdventrue\\src\\snd\\jump.wav"));
			AudioFormat aif = ais.getFormat();
			final SourceDataLine sdl;
			DataLine.Info info = new DataLine.Info(SourceDataLine.class, aif);
			sdl = (SourceDataLine) AudioSystem.getLine(info);
			sdl.open(aif);
			sdl.start();
			FloatControl fc = (FloatControl) sdl.getControl(FloatControl.Type.MASTER_GAIN);
			int nByte = 0;
			final int SIZE = 1024 * 64;
			byte[] buffer = new byte[SIZE];
			while (nByte != -1) {
				nByte = ais.read(buffer, 0, SIZE);
				sdl.write(buffer, 0, nByte);
			}
			sdl.stop();
		} catch (Exception e) {
			// TODO: handle exception

			e.printStackTrace();
		}
	}

	/**
	 * ª«Ú“Ù
	 */
	public static void playCoin() {
		try {
			AudioInputStream ais = AudioSystem
					.getAudioInputStream(new File("D:\\E work\\MarioAdventrue\\src\\snd\\coin.wav"));
			AudioFormat aif = ais.getFormat();
			final SourceDataLine sdl;
			DataLine.Info info = new DataLine.Info(SourceDataLine.class, aif);
			sdl = (SourceDataLine) AudioSystem.getLine(info);
			sdl.open(aif);
			sdl.start();
			FloatControl fc = (FloatControl) sdl.getControl(FloatControl.Type.MASTER_GAIN);
			int nByte = 0;
			final int SIZE = 1024 * 64;
			byte[] buffer = new byte[SIZE];
			while (nByte != -1) {
				nByte = ais.read(buffer, 0, SIZE);
				sdl.write(buffer, 0, nByte);
			}
			sdl.stop();
		} catch (Exception e) {
			// TODO: handle exception

			e.printStackTrace();
		}
	}

	/**
	 * À¿Õˆ“Ù
	 */
	public static void playDeath() {
		try {
			AudioInputStream ais = AudioSystem
					.getAudioInputStream(new File("D:\\E work\\MarioAdventrue\\src\\snd\\À¿Õˆ.wav"));
			AudioFormat aif = ais.getFormat();
			final SourceDataLine sdl;
			DataLine.Info info = new DataLine.Info(SourceDataLine.class, aif);
			sdl = (SourceDataLine) AudioSystem.getLine(info);
			sdl.open(aif);
			sdl.start();
			FloatControl fc = (FloatControl) sdl.getControl(FloatControl.Type.MASTER_GAIN);
			int nByte = 0;
			final int SIZE = 1024 * 64;
			byte[] buffer = new byte[SIZE];
			while (nByte != -1) {
				nByte = ais.read(buffer, 0, SIZE);
				sdl.write(buffer, 0, nByte);
			}
			sdl.stop();
		} catch (Exception e) {
			// TODO: handle exception

			e.printStackTrace();
		}
	}

	/**
	 * —≠ª∑
	 */
	public static void loop() {
		new Thread(new Runnable() {
			@Override
			public void run() {
				new Start();
				// TODO Auto-generated method stub
				while (Start.index == 0) {
					playBackground();
				}
			}
		}).start();
	}

	public static void main(String[] args) throws MalformedURLException, IllegalArgumentException {
		// TODO Auto-generated method stub
		loop();
		Music.playJump();
		Music.playCoin();
		Music.playDeath();

	}

}
