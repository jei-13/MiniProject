import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class EditPanel extends JPanel{
	private JTextField tf = new JTextField(10);
	private TextSource textSource = null;
	private ImageIcon icon = new ImageIcon("d:\\pai.png"); 
    private Image img = icon.getImage(); 
    
	public EditPanel(TextSource textSource) {
		this.textSource = textSource;
		this.setBackground(Color.WHITE);
		add(tf);
		JButton btn = new JButton("추가");
		add(btn);
		
		btn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				String word = tf.getText(); //문자 입력받기
				if(word.length() ==0)
					return;
				
				textSource.add(word); //추가
			}
		});
	}
	@Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g); 
        g.drawImage(img, 0, 0, getWidth(), getHeight(), this); 
    }
}
