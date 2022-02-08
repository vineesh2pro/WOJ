package Tester;

import java.awt.*;
import java.util.Collections;
import javax.swing.*;
import javax.swing.plaf.metal.MetalScrollBarUI;

public class RemoveArrowButtonsTest {
  private Component makeUI() {
    UIManager.put("ScrollBar.incrementButtonGap", 0);
    UIManager.put("ScrollBar.decrementButtonGap", 0);
    String txt = String.join("\n", Collections.nCopies(100, "1234567890"));
    JPanel p = new JPanel(new GridLayout(1, 0));
    p.add(new JScrollPane(new JTextArea(txt)));
    p.add(new JScrollPane(new JTextArea(txt)) {
      @Override public void updateUI() {
        super.updateUI();
        getVerticalScrollBar().setUI(new WithoutArrowButtonScrollBarUI());
        getHorizontalScrollBar().setUI(new WithoutArrowButtonScrollBarUI());
      }
    });
    return p;
  }

  public static void main(String[] args) {
    EventQueue.invokeLater(() -> {
      JFrame f = new JFrame();
      f.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
      f.getContentPane().add(new RemoveArrowButtonsTest().makeUI());
      f.setSize(320, 240);
      f.setLocationRelativeTo(null);
      f.setVisible(true);
    });
  }
}

class ZeroSizeButton extends JButton {
  @Override public Dimension getPreferredSize() {
    return new Dimension();
  }
}

class WithoutArrowButtonScrollBarUI extends MetalScrollBarUI {
  @Override protected JButton createDecreaseButton(int orientation) {
    return new ZeroSizeButton();
  }

  @Override protected JButton createIncreaseButton(int orientation) {
    return new ZeroSizeButton();
  }
}