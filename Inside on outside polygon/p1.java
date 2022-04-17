import java.util.*;
import java.lang.*;
import java.io.*;
class p1
{
    static int INF=100000;
    static class Point
    {
        int x,y;
        public Point(int x,int y)
        {
            this.x=x;
            this.y=y;
        }
    };
    static boolean onseg(Point p,Point q,Point r)
    {
        if(q.x<=Math.max(p.x,r.x) && q.x>=Math.min(p.x,r.x) && q.y<=Math.max(p.y,r.y) && q.y>=Math.min(p.y,r.y))
            return true;
        return false;
    }
    static int orientation(Point p,Point q,Point r)
    {
        int val=(q.y-p.y)*(r.x-q.x)-(q.x-p.x)*(r.y-q.y);
        if(val==0)
            return 0;
        return (val>0)?1:2;
    }
    static boolean dointersect(Point p,Point q,Point r,Point s)
    {
        int o1=orientation(p,q,r);
        int o2=orientation(p,q,s);
        int o3=orientation(r,s,p);
        int o4=orientation(r,s,q);
        if(o1!=o2 && o3!=o4)
            return true;
        if(o1==0 && onseg(p,r,q))
            return true;
        if(o2==0 && onseg(p,s,q))
            return true;
        if(o3==0 && onseg(r,p,s))
            return true;
        if(o4==0 && onseg(r,q,s))
            return true;
        return false;
    }
    static boolean inside(Point poly[],int n,Point p)
    {
        if(n<3)return false;
        Point extreme=new Point(INF,p.y);
        int count=0,i=0;
        do
        {
            int next=(i+1)%n;
            //if(next==n)next=0;
            if(dointersect(poly[i],poly[next],p,extreme))
            {
                if(orientation(poly[i],p,poly[next])==0)
                    return onseg(poly[i],p,poly[next]);
                count++;
            }
            i=next;
        }while(i!=0);
        return (count % 2 == 1);
    }
    public static void main(String args[])
    {
        Point p[]={new Point(0,0),new Point(0,1),new Point(1,1),new Point(1,0)};
        int n=p.length;
        if(inside(p,n,new Point(5,5)))
            System.out.println("Inside");
        else
            System.out.println("Outside");
    }
}