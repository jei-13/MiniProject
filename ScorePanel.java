import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class ScorePanel extends JPanel {
	private int score=0;
	private int fishnum=0;
	private int time = 40;
	private JLabel scoreLabel = new JLabel(Integer.toString(score));
	private JLabel fishLabel = new JLabel(Integer.toString(fishnum));
	private ImageIcon icon = new ImageIcon("d:\\ship.png"); 
    private Image img = icon.getImage(); 
    public TimerThread th;
	public JLabel timerLabel;
	
	public ScorePanel() {
		this.setBackground(Color.WHITE);
		this.setLayout(null);
		
		JLabel scoreTextLabel = new JLabel("점수");
        scoreTextLabel.setBounds(20, 20, 50, 20); // 위치와 크기 설정 
        add(scoreTextLabel);

        scoreLabel.setBounds(80, 20, 50, 20);
        add(scoreLabel);

        JLabel fishTextLabel = new JLabel("잡은 물고기");
        fishTextLabel.setBounds(20, 50, 100, 20); 
        add(fishTextLabel);

        fishLabel.setBounds(130, 50, 50, 20); 
        add(fishLabel);
        
        timerLabel = new JLabel("40");
        timerLabel.setFont(new Font("Gothic",Font.ITALIC,45));
        timerLabel.setBounds(210, 0, 100, 100);
        add(timerLabel);
        th = new TimerThread(timerLabel,this); //타임스레드
	}
	
	public void startTimer() {
		th.start(); //시간 측정 시작
	}
	
	@Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g); 
        g.drawImage(img, 50, 70, 200, 200, this); 
    }

	public void increase(int point,int fish) { //점수,물고기 누적 저장
		score +=point;
		scoreLabel.setText(Integer.toString(score));
		
		fishnum+=fish;
		fishLabel.setText(Integer.toString(fishnum));
	}
	
	public int finalscore() { //최종 점수
		return score*fishnum;
	}
}

