import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class GamePanel extends JPanel { //easy버전
	private JLabel fallingLabel = new JLabel("Hello"); //이거를 맨위에써야
    private GameGroundPanel ground = new GameGroundPanel(); 
    private InputPanel input = new InputPanel();
    private ScorePanel scorePanel = null;
    private TextSource textSource = null;
    private Initgame game = null;
    public FallingThread fThread = new FallingThread();
    private Random random = new Random();
    
    public GamePanel(ScorePanel scorePanel, TextSource textSource) {
        this.scorePanel = scorePanel;
        this.textSource = textSource;

        setLayout(new BorderLayout());
        add(ground, BorderLayout.CENTER);
        add(input, BorderLayout.SOUTH);
    }

    public void startGame() {
        fThread.start(); 
    }

    public void newWord() { // 새로운 단어 추가
    	ImageIcon[] image = { //이미지 배열
    			new ImageIcon("d:\\cut.png"),
    			new ImageIcon("d:\\ga.png"),
    			new ImageIcon("d:\\any.png"),
    			new ImageIcon("d:\\jelly.png"),
    			new ImageIcon("d:\\sau.png"),
    			new ImageIcon("d:\\ray.png")
    	};
    	
    	int index = random.nextInt(image.length); //이미지 랜덤으로 저장
    	ImageIcon select = image[index];
    	
	    JLabel newLabel = new JLabel(textSource.get(),select,JLabel.CENTER); //단어 옆에 이미지 추가
	
	    newLabel.setSize(150, 60);
	    int x = random.nextInt(ground.getWidth() - newLabel.getWidth()); // 랜덤 x 위치
	    newLabel.setLocation(x, 0); // 랜덤 위치에서 시작
	    ground.add(newLabel);
	    
        FallingLabelThread thread = new FallingLabelThread(newLabel);
    	
        thread.start();
    }

    class GameGroundPanel extends JPanel { //게임화면
        private ImageIcon icon = new ImageIcon("d:\\sea1.jpg");
        private Image img = icon.getImage();

        public GameGroundPanel() {
            setLayout(null);
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            g.drawImage(img, 0, 0, getWidth(), getHeight(), this);
        }
    }

    class FallingThread extends Thread { // 단어 생성 주기
    	
        @Override
        public void run() {
            while (true) {
                newWord();
                try {
                    sleep(2000); // 2초 간격으로 새로운 단어 추가
                    
                    
                } catch (InterruptedException e) {
                    return;
                }
            }
        }
    }

    class FallingLabelThread extends Thread { // 개별 단어 떨어지는 속도
        private JLabel label;
       
    	
        public FallingLabelThread(JLabel label) {
            this.label = label;
        }

        @Override
        public void run() {
            while (label.getY() < ground.getHeight()) { 
                label.setLocation(label.getX(), label.getY() + 10); //10픽셀씩 아래로 이동
                try {
                    sleep(600); // 단어 떨어지는 속도
                   
                } catch (InterruptedException e) {
                    return;
                }
            }
        }
    }

    class InputPanel extends JPanel {
        private JTextField tf = new JTextField(10);

        public InputPanel() {
            this.setBackground(Color.LIGHT_GRAY);
            add(tf);
            tf.addActionListener(new ActionListener() {

                @Override
                public void actionPerformed(ActionEvent e) {
                    JTextField t = (JTextField) e.getSource();
                    String text = t.getText();
                    if (text.length() == 0)
                        return;

                        for (int i = 0; i < ground.getComponentCount(); i++) {
                            JLabel label = (JLabel) ground.getComponent(i);
                            if (text.equals(label.getText())) { // 성공 시
                            	int score=0;
                            	int fish=0;
                            	
                            	ImageIcon icon = (ImageIcon) label.getIcon(); //단어 옆 이미지 가져오기
                            	String dec = icon.getDescription(); //사진에 대한 설명 반환
                            	
                            	//설명과 일치하는 사진 찾아서 점수 부여
                            	if(dec.contains("ga.png") || dec.contains("cut.png") || dec.contains("sau.png")) {
                            		score=100;
                            		fish=1;
                            	}
                            	else if(dec.contains("any.png") || dec.contains("ray.png")) {
                            		score=200;
                            		fish=1;
                            	}
                            	else if(dec.contains("jelly.png")) {
                            		score=-50;
                                    //fish 수 변화 없음
                            	}
                            	
                                scorePanel.increase(score,fish); //점수, 물고기 수 반영
                                ground.remove(label);
                                ground.repaint();
                                break;
                            }
                        }
                    
                    t.setText(""); // 현재 입력된 내용 지우기
                }
            });
        }
    }
}
