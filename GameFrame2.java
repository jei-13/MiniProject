import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JSplitPane;
import javax.swing.JToolBar;

public class GameFrame2 extends JFrame { //hard버전 화면
	private TextSource textSource = new TextSource();
	private ScorePanel scorePanel = new ScorePanel();
	private EditPanel2 editPanel = new EditPanel2(textSource);
	private GamePanel2 gamePanel = new GamePanel2(scorePanel, textSource);
	
	public GameFrame2() { //누구에게 포함되지않는 탑레벨 컨테이너
		setTitle("게임");
		setSize(900,600);
		setResizable(false); //크기 변경 못하게 하기
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //완전히 종료
		makeMenu();
		makeToolBar();
		makeSplit();
		setVisible(true);
	
	}
	
	private void makeMenu() { //메뉴 바
		JMenuBar mb = new JMenuBar(); //프레임에 붙임
		this.setJMenuBar(mb); //메뉴바는 한개뿐이기에 add가 아닌 set
		
		JMenu fileMenu = new JMenu("File");
		mb.add(fileMenu); //메뉴바에 메뉴 추가
		
		JMenu editMenu = new JMenu("Edit");
		mb.add(editMenu);
		
		JMenuItem startItem = new JMenuItem("Start");
		fileMenu.add(startItem); //메뉴 안에 메뉴아이템 추가
		
		//스타드 버튼 눌러야 단어가 나옴
		startItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				gamePanel.startGame();
				scorePanel.startTimer();
			}
		});
		
		JMenuItem stopItem = new JMenuItem("Stop");
		fileMenu.add(stopItem);
		JMenuItem exitItem = new JMenuItem("Exit");
		fileMenu.add(exitItem);
		
	}
	
	private void makeSplit() {
		JSplitPane hPane = new JSplitPane(); //팬 나누기
		hPane.setOrientation(JSplitPane.HORIZONTAL_SPLIT);
		hPane.setDividerLocation(550); //크기 설정
		getContentPane().add(hPane, BorderLayout.CENTER);
		
		JSplitPane vPane = new JSplitPane();
		vPane.setOrientation(JSplitPane.VERTICAL_SPLIT);
		vPane.setDividerLocation(250);
		hPane.setRightComponent(vPane);
		
		vPane.setTopComponent(scorePanel);
		vPane.setBottomComponent(editPanel);
		hPane.setLeftComponent(gamePanel);
	}
	
	private void makeToolBar() { //컨텐트팬에 붙임
		JToolBar tBar = new JToolBar();
		tBar.setFloatable(false); //핸들 없앰(툴바 고정)
		getContentPane().add(tBar, BorderLayout.NORTH);
		
		JButton startBtn = new JButton("Start");
		tBar.add(startBtn); //버튼 옆에잇는걸 핸들 이라 부름. 눌러서 움직이기 가능
		
		startBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				gamePanel.startGame();
				scorePanel.startTimer();
			}
		});
	}
	
	public static void main(String[] args) {
		//new GameFrame();

	}

}
