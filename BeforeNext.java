import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class BeforeNext extends JFrame { //게임 종료 후 띄우는 창
	private MyPanel panel;
	private RankSource rankSource;
	
	public BeforeNext(ScorePanel scorePanel) {
		setTitle("게임 종료");
		setSize(900,600);
		setResizable(false); 
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
		rankSource = new RankSource("ranking.txt");
		panel = new MyPanel(scorePanel);
		setContentPane(panel);
		setVisible(true);
	}
	
	class MyPanel extends JPanel{
		private ImageIcon icon = new ImageIcon("d:\\middle.jpg");
		private Image img = icon.getImage(); 
		private ScorePanel scorePanel; //점수 전달받기 위함
		
		public MyPanel(ScorePanel scorePanel) {
			this.scorePanel = scorePanel;
			setLayout(null);
			
			//버튼 생성
			BlockButton btn = new  BlockButton(Color.WHITE, Color.BLACK);
			btn.setText("랭킹 보기");
			btn.setLocation(290,350);
			btn.setSize(200, 50);
			add(btn);
			
			BlockButton btn2 = new  BlockButton(Color.WHITE, Color.BLACK);
			btn2.setText("시작 화면으로");
			btn2.setLocation(440,350);
			btn2.setSize(200, 50);
			add(btn2);
			
			//이름,점수 출력
			JLabel nt = new JLabel("이름:"+Initgame.name);
			nt.setLocation(400, 130);
			nt.setSize(200,100);
			add(nt);
			
			JLabel fs = new JLabel("총 점수: " + scorePanel.finalscore());
			fs.setLocation(350, 184);
			fs.setSize(500,100);
			fs.setFont(new Font("한컴 고딕", Font.PLAIN, 23));
			add(fs);
			
			rankSource.saveScore(Initgame.name, scorePanel.finalscore()); //현재 이름,점수 저장해서 넘김
			 
			btn.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					new RankFrame(rankSource); //랭킹 화면 출력
				}
			});
			
			 
			btn2.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					dispose();
					new Initgame(); //시작 화면으로 돌아가기
				}
			});
			
		}

		@Override
		public void paintComponent(Graphics g) {
			super.paintComponent(g);
			g.drawImage(img, 0, 0, getWidth(), getHeight(), this);
			
			g.setFont(new Font("휴먼둥근헤드라인", Font.PLAIN, 45));
			g.drawString("게임 종료", 325, 95);
		}
	}
	
	public static void main(String[] args) {
		ScorePanel scorePanel = new ScorePanel();
        
		new BeforeNext(scorePanel);
	}
}
