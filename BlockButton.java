import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.RenderingHints;

import javax.swing.JButton;

public class BlockButton extends JButton { //버튼 디자인 구현
	    private final Color unClickBackground;
	    private final Color foreground;

	    int paddingWidth = 10, paddingHeight = 2; //버튼 텍스트 주변 여백

	    public BlockButton(Color unClickBackground, Color foreground) {
	        this.unClickBackground = unClickBackground;
	        this.foreground = foreground;

	        setText("game start");

	        Dimension dimension = getPreferredSize(); //기본크기에 여백 추가
	        int w = (int) dimension.getWidth() + paddingWidth * 3;
	        int h = (int) dimension.getHeight() + paddingHeight * 3;

	        setPreferredSize(new Dimension(w, h));
	        setOpaque(false);
	        setBorder(null); //기본 테두리 제거
	        setBackground(unClickBackground);
	        setForeground(foreground);
	       
	    }

	    @Override
	    public void paintComponent(Graphics g) {
	        Graphics2D g2 = (Graphics2D) g; //그래픽 기능 사용함
	        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

	        Dimension dimension = getPreferredSize();
	        int w = (int) dimension.getWidth();
	        int h = (int) dimension.getHeight();
	        
	        g2.setColor(getBackground());
	        g2.fillRoundRect(0, 0, w, h, 20, 20); //둥근 모서리로 변경

	        g2.setColor(getForeground());
	        g2.setFont(new Font("맑은 고딕", 1, 15));

	        FontMetrics fontMetrics = g2.getFontMetrics();
	        Rectangle rectangle = fontMetrics.getStringBounds(getText(), g2).getBounds();
	        
	        //버튼 중앙에 텍스트 배치
	        g2.drawString(getText(), (w - rectangle.width) / 2, (h - rectangle.height) / 2 + fontMetrics.getAscent());
	    }
	}
