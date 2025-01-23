import javax.swing.JLabel;

public class TimerThread extends Thread{ //타이머 스레드
	private JLabel timerLabel; //타이머 값 출력
	private ScorePanel scorePanel;
	private Thread fallingThread;
	
	public TimerThread(JLabel timerLabel,ScorePanel scorePanel ) {
		this.timerLabel = timerLabel; //타이머 카운트 출력
		this.scorePanel = scorePanel;
		this.fallingThread = fallingThread;
	}
	
	@Override
	public void run() {
		int n=40;
		while(n>=0) {
			timerLabel.setText(Integer.toString(n));
			n--;
			try {
				Thread.sleep(1000); //1초 간격
			}
			catch(InterruptedException e) {
				return;
			}
		}
		
		
		try {
			sleep(500); //멈춤
		} catch (InterruptedException e) {
			return;
		}
		
		fallingThread.interrupt();
		new BeforeNext(scorePanel); //시간 0 되면 라운드 종료 창 띄우기
	}
}
