
package instructions;

import java.io.*;
import java.awt.*;
import java.awt.geom.*;
import java.awt.event.*;
import javax.swing.*;
import javax.sound.sampled.*;

public class instructions {
    public void line(Graphics2D g, int xleft,int ytop, String _string)
    {
        g.setColor(Color.black);
        g.setFont(new Font("Monospaced",Font.BOLD,29));
        g.drawString(_string, xleft, ytop);
    }
//    public void line2(Graphics2D g, int xleft,int ytop)
//    {
//        g.setColor(Color.black);
//        g.setFont(new Font("Monospaced",Font.BOLD,29));
//        g.drawString("It can move diagonaly one space in each", xleft, ytop);
//    }
//    public void line3(Graphics2D g, int xleft,int ytop)
//    {
//        g.setColor(Color.black);
//        g.setFont(new Font("Monospaced",Font.BOLD,29));
//        g.drawString("direction.The purpose of the priest is", xleft, ytop);
//    }
//    public void line4(Graphics2D g, int xleft,int ytop)
//    {
//        g.setColor(Color.black);
//        g.setFont(new Font("Monospaced",Font.BOLD,29));
//        g.drawString("conversion.Conversion is when one peice changes", xleft, ytop);
//    }
//    public void line5(Graphics2D g, int xleft,int ytop)
//    {
//        g.setColor(Color.black);
//        g.setFont(new Font("Monospaced",Font.BOLD,29));
//        g.drawString("and joins the other side.The chance is ", xleft, ytop);
//    }
//    public void line6(Graphics2D g, int xleft,int ytop)
//    {
//        g.setColor(Color.black);
//        g.setFont(new Font("Monospaced",Font.BOLD,29));
//        g.drawString("dependent on loyalty.Loyalty can be seen when  ", xleft, ytop);
//    }
//    public void line7(Graphics2D g, int xleft,int ytop)
//    {
//        g.setColor(Color.black);
//        g.setFont(new Font("Monospaced",Font.BOLD,29));
//        g.drawString("the priest is selected.Loyaty of a piece is", xleft, ytop);
//    }
//    public void line8(Graphics2D g, int xleft,int ytop)
//    {
//        g.setColor(Color.black);
//        g.setFont(new Font("Monospaced",Font.BOLD,29));
//        g.drawString("lowered when a piece dies.Loyalty is returned", xleft, ytop);
//    }
//    public void line9(Graphics2D g, int xleft,int ytop)
//    {
//        g.setColor(Color.black);
//        g.setFont(new Font("Monospaced",Font.BOLD,29));
//        g.drawString("at the end of a move.When loyalty is high it ", xleft, ytop);
//    }
//    public void line10(Graphics2D g, int xleft,int ytop)
//    {
//        g.setColor(Color.black);
//        g.setFont(new Font("Monospaced",Font.BOLD,29));
//        g.drawString("is harder to be converted.Possible movements.", xleft, ytop);
//    }
//    public void line11(Graphics2D g, int xleft,int ytop)
//    {
//        g.setColor(Color.black);
//        g.setFont(new Font("Monospaced",Font.BOLD,29));
//        g.drawString("for all pieces are indicated by green squares.", xleft, ytop);
//    }
//    public void line12(Graphics2D g, int xleft,int ytop)
//    {
//        g.setColor(Color.black);
//        g.setFont(new Font("Monospaced",Font.BOLD,29));
//        g.drawString("Speacial moves like castling and en passant.", xleft, ytop);
//    }
//    public void line13(Graphics2D g, int xleft,int ytop)
//    {
//        g.setColor(Color.black);
//        g.setFont(new Font("Monospaced",Font.BOLD,29));
//        g.drawString("are indicated by yellow squares", xleft, ytop);
//    }
//     public void line14(Graphics2D g, int xleft,int ytop)
//    {
//        g.setColor(Color.black);
//        g.setFont(new Font("Monospaced",Font.BOLD,29));
//        g.drawString("", xleft, ytop);
//    }
//      public void line15(Graphics2D g, int xleft,int ytop)
//    {
//        g.setColor(Color.black);
//        g.setFont(new Font("Monospaced",Font.BOLD,29));
//        g.drawString("", xleft, ytop);
//    }
//       public void line16(Graphics2D g, int xleft,int ytop)
//    {
//        g.setColor(Color.black);
//        g.setFont(new Font("Monospaced",Font.BOLD,29));
//        g.drawString("", xleft, ytop);
//    }
}
