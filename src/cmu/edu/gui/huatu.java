package cmu.edu.gui;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.*;
import java.util.List;
import javax.swing.*;
public class huatu extends JFrame{
    //画布
    JPanel aa=new JPanel(null);
    JPanel bb=new JPanel(null);
    JPanel cc=new JPanel(null);
    //一个装载图形bai的容器
    List <shape> list=new ArrayList<shape>();
    Iterator <shape> it=list.iterator();
    //Panel a=(Panel) this.getContentPane();
//标记绘制那个图形
    int flag=0;
    //按钮
    JButton line=new JButton("直线");
    JButton cicle=new JButton("圆");
    JButton rect=new JButton("方形");
    JButton ov=new JButton("椭圆");
    JButton pen=new JButton("铅笔");
    //绘图的坐标
    int startx=0;
    int starty=0;
    int endx=0;
    int endy=0;
    //判断是否是第一次绘图
    boolean firsthui =false;


    public void paint(Graphics g){
//g=aa.getGraphics();
        it=list.iterator();
        for(int i=0;i<list.size();i++){
//list.get(i);
            shape a=it.next();
            a.draw(g);
            System.out.print("111");
        }
//g.drawLine(10, 10, 100, 100);
    }


    //构造函数
    public huatu(){
        init();
    }
    //窗口初始化工作
    public void init(){
//鼠标监听器
        huatuMouseLisner hm=new huatuMouseLisner();
        this.addMouseListener(hm);
        this.add(aa);
//aa.setBounds(0, 0, 580, 590);
//
        huatuMouseMotionListener hmo=new huatuMouseMotionListener();
        this.addMouseMotionListener(hmo);
        aa.add(bb);
        bb.setBounds(0, 0, 70, 590);
//添加按钮
        bb.add(line);
        line.setBounds(0, 0, 70, 30);
        bb.add(cicle);
        cicle.setBounds(0, 30, 70,30);
        bb.add(rect);
        rect.setBounds(0, 60, 70, 30);
        bb.add(ov);
        ov.setBounds(0, 90, 70, 30);
        bb.add(pen);
        pen.setBounds(0, 120, 70, 30);
        aa.add(cc);
        cc.setBounds(70, 0, 510, 590);
//添加按钮响应
//画线
        ActionListener l=new ActionListener(){
            public void actionPerformed(ActionEvent arg0) {
// TODO Auto-generated method stub
                flag=1;
            }
        };
//画圆
        ActionListener c=new ActionListener(){
            public void actionPerformed(ActionEvent arg0) {
// TODO Auto-generated method stub
                flag=2;
            }
        };
//画方块
        ActionListener r=new ActionListener(){
            public void actionPerformed(ActionEvent arg0) {
// TODO Auto-generated method stub
                flag=3;
//System.out.println(flag);
            }
        };
//画椭圆
        ActionListener o=new ActionListener(){
            public void actionPerformed(ActionEvent arg0) {
// TODO Auto-generated method stub
                flag=4;
            }
        };
        line.addActionListener(l);
        cicle.addActionListener(c);
        rect.addActionListener(r);
        ov.addActionListener(o);
//this.add(aa);
//aa.setBounds(100, 0, 500, 500);
        this.setSize(590,580);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//将窗体设置为大小不可改变
//this.setResizable(false);
        this.setVisible(true);
    }


    class huatuMouseLisner implements MouseListener{
        public void mouseClicked(MouseEvent arg0) {
// TODO Auto-generated method stub
        }
        public void mouseEntered(MouseEvent arg0) {
// TODO Auto-generated method stub
        }
        public void mouseExited(MouseEvent arg0) {
// TODO Auto-generated method stub
        }
        public void mousePressed(MouseEvent e) {
// TODO Auto-generated method stub
            firsthui=true;
            startx=e.getX();
            starty=e.getY();
        }
        public void mouseReleased(MouseEvent e) {
// TODO Auto-generated method stub
            endx=e.getX();
            endy=e.getY();
            firsthui=false;
            if(flag==1){
                list.add(new line(startx,starty,endx,endy));
            }
            if(flag==2){
                list.add(new cicle(startx,starty,endx,endy));
            }
            if(flag==3){
                list.add(new rect(startx,starty,endx,endy));
            }
            if(flag==4){
                list.add(new ov(startx,starty,endx,endy));
            }
//System.out.println("111"+firsthui);
//Graphics g =aa.getGraphics();
//g.drawLine(startx, starty, endx, endy);
        }
    }
    class huatuMouseMotionListener implements MouseMotionListener{
        public void mouseDragged(MouseEvent e) {
// TODO Auto-generated method stub
            Graphics g =getGraphics();
            g.setXORMode(getBackground());
//System.out.println(firsthui);
            switch (flag){
                case 1: if(firsthui==true){
                    endx=e.getX();
                    endy=e.getY();
                    firsthui=false;
                    g.drawLine(startx, starty, endx, endy);
                }
                else{
                    g.drawLine(startx, starty, endx, endy);
                    endx=e.getX();
                    endy=e.getY();
                    g.drawLine(startx, starty, endx, endy);
                }
                    break;

                case 2: if(firsthui==true){
                    endx=e.getX();
                    endy=e.getY();
                    firsthui=false;
                    g.drawOval(startx, starty, Math.abs(endx-startx), Math.abs(endx-startx));
                }
                else{
                    g.drawOval(startx, starty, Math.abs(endx-startx), Math.abs(endx-startx));
                    endx=e.getX();
                    endy=e.getY();
                    g.drawOval(startx, starty, Math.abs(endx-startx), Math.abs(endx-startx));
                }
                    break;
                case 3:
                    if(firsthui==true){
                        endx=e.getX();
                        endy=e.getY();
                        firsthui=false;
                        g.drawRect(startx, starty, Math.abs(endx-startx), Math.abs(endy-starty));
                    }
                    else{
                        g.drawRect(startx, starty, Math.abs(endx-startx), Math.abs(endy-starty));
                        endx=e.getX();
                        endy=e.getY();
                        g.drawRect(startx, starty, Math.abs(endx-startx), Math.abs(endy-starty));
                    }
                    break;
                case 4:
                    if(firsthui==true){
                        endx=e.getX();
                        endy=e.getY();
                        firsthui=false;
                        g.drawOval(startx, starty, Math.abs(endx-startx), Math.abs(endy-starty));
                    }
                    else{
                        g.drawOval(startx, starty, Math.abs(endx-startx), Math.abs(endy-starty));
                        endx=e.getX();
                        endy=e.getY();
                        g.drawOval(startx, starty, Math.abs(endx-startx), Math.abs(endy-starty));
                    }
                    break;
            }
        }
        public void mouseMoved(MouseEvent e) {
// TODO Auto-generated method stub
        }
    }
    public static void main(String args[]){
        huatu a=new huatu();
    }
}
class shape {
    public void draw(Graphics g){
    }
}
class line extends shape{
    int x,y,r,m;
    public line(int i,int j,int k,int l){
        x=i;
        y=j;
        r=k;
        m=l;
    }
    public void draw(Graphics g){
        g.drawLine(x, y, r, m);
    }
}
class cicle extends shape{
    int x,y,r;
    public cicle(int i,int j,int k,int l){
        x=i;
        y=j;
        r=Math.abs(k-i);
    }
    public void draw(Graphics g){
        g.drawOval(x, y, r, r);
    }
}
class ov extends shape{
    int x,y,r,m;
    public ov(int i,int j,int k,int l){
        x=i;
        y=j;
        r=Math.abs(k-i);
        m=Math.abs(l-j);
    }
    public void draw(Graphics g){
        g.drawOval(x, y, r, m);
    }
}
class rect extends shape{
    int x,y,r1,r2;
    public rect(int i,int j,int k,int l){
        x=i;
        y=j;
        r1=Math.abs(k-i);
        r2=Math.abs(l-j);
    }
    public void draw(Graphics g){
        g.drawRect(x, y, r1, r2);
    }
}