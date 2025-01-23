import java.awt.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;

import javax.swing.*;

public class Initgame extends JFrame{ //시작 화면
	private MyPanel panel = new MyPanel();
	public static String name;
	private RankSource rankSource;
	
	public Initgame() {
		setTitle("게임");
		setSize(900,600);
		setResizable(false); 
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
		rankSource = new RankSource("ranking.txt"); //txt파일 가져오기
		setContentPane(panel);
		panel.setLayout(null);
		
		setVisible(true);
	}
	
	class MyPanel extends JPanel{
		private ImageIcon icon = new ImageIcon("d:\\screen.jpg");
		private Image img = icon.getImage(); 
	
		private JTextField tf = new JTextField(20); //이름 입력칸
		private MyDialog dialog;
		
		private String [] level = {"easy", "hard"}; //콤보박스 아이템
		private JComboBox<String> strCom = new JComboBox<String>(level); //문자열 콤보박스 생성
		public int index=0; //인덱스 번호
		
		public MyPanel() {
			//버튼 생성
			BlockButton btn = new  BlockButton(Color.WHITE, Color.BLACK);
			btn.setText("게임 시작");
			btn.setLocation(80,340);
			btn.setSize(130, 50);
			add(btn);

			BlockButton exp = new  BlockButton(Color.WHITE,Color.BLACK);
			exp.setText("게임 설명");
			exp.setLocation(80,390);
			exp.setSize(130, 50);
			dialog = new MyDialog(this, "explain rules"); //다이얼로그 생성
			
			BlockButton rank = new  BlockButton(Color.WHITE, Color.BLACK);
			rank.setText("랭킹 보기");
			rank.setLocation(80,440);
			rank.setSize(130, 50);
			
			strCom.setLocation(80, 260);
			strCom.setSize(128,37);
			add(strCom);
			
			strCom.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					JComboBox<String> cb = (JComboBox<String>)e.getSource(); //이벤트 발생 콤보박스 알아내기
					index = cb.getSelectedIndex(); //선택된 아이템의 인덱스 번호
					
			     }
			});
			
			tf.setSize(83,28);
			tf.setLocation(125,304);
			add(tf);
			
			btn.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					name=tf.getText(); //이름 저장
					
					if (index == 0) {
			            new GameFrame(); // easy 모드 실행
			        } else if (index == 1 ){
			            new GameFrame2(); // hard 모드 실행
			        }

					dispose(); //현재 창 제거
				}
			});
			
			exp.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					dialog.setVisible(true);
				}
			});
			add(exp);
			
			rank.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					new RankFrame(rankSource); //랭킹 화면 출력
				}
			});
			add(rank);
		}
		
		@Override
		public void paintComponent(Graphics g) { //화면 꽉차게 이미지 조절
			super.paintComponent(g);
			g.drawImage(img, 0, 0, getWidth(), getHeight(), this);
			g.setFont(new Font("맑은 고딕",Font.BOLD,17));
			g.drawString("이름:", 80,325);
		}
	}
	
	class MyDialog extends JDialog{
		private BlockButton ok = new BlockButton(Color.WHITE,Color.BLACK);
		
		public MyDialog(Initgame.MyPanel myPanel, String title) {
			super();
			setLayout(new FlowLayout());
			
			JLabel imageL = new JLabel(new ImageIcon("d:\\explain.jpg"));
			add(imageL, BorderLayout.CENTER);
			
			ok.setText("OK");
			add(ok);
			setSize(740,530);
			
			ok.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					setVisible(false);
				}
			});
		}
	}
	
	public static void main(String[] args) {
		new Initgame();
	}

}
