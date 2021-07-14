public class Body {
    public double xxPos;
    public double yyPos;
    public double xxVel;
    public double yyVel;
    public double mass;
    public String imgFileName;
    public Body(double xP, double yP, double xV,double yV, double m, String img){
        xxPos=xP;yyPos=yP;xxVel=xV;yyVel=yV;mass=m;imgFileName=img;
    }
    public Body(Body b){
        xxPos = b.xxPos;
        yyPos = b.yyPos;
        xxVel = b.xxVel;
        yyVel = b.yyVel;
        mass = b.mass;
        imgFileName = b.imgFileName;
    }
    public double calcDistance(Body b){
        double x=this.xxPos-b.xxPos;
        double y=this.yyPos-b.yyPos;
        //System.out.println(x+"这是qwe"+y);
        double d=Math.hypot(x,y);
        return d;
    }
    public double calcForceExertedBy(Body b){
        if (this.equals(b)) {
            return 0;
        }
        double G = 6.67e-11;
        double r=this.calcDistance(b);
        double F=-(G*this.mass*b.mass)/Math.pow(r,2);
        return F;
    }
    public double calcForceExertedByX(Body b){
        // double G = 6.67e-11;
        double dx=this.xxPos-b.xxPos;
        if (dx==0) {return 0;}
        dx=dx>0?dx:-dx;
        // double Fx=(G*this.mass*b.mass)/Math.pow(dx,2);
        /* The title states that the following method of
        calculation is to be used instead of the above method*/
        double Fx = this.calcForceExertedBy(b) * dx / this.calcDistance(b);
        return Fx;
    }
    public double calcForceExertedByY(Body b){
        // double G = 6.67e-11;
        double dy=this.yyPos-b.yyPos;
        if (dy==0) {return 0;}
        dy=dy>0?dy:-dy;
        // double Fy=(G*this.mass*b.mass)/Math.pow(dy,2);
        double Fy = this.calcForceExertedBy(b) * dy / this.calcDistance(b);
        return Fy;
    }

    public double calcNetForceExertedByX(Body[] allBodys){
        double netX=0;
        for (Body b:allBodys) {
            if(this.equals(b)){continue;}
            netX+=this.calcForceExertedByX(b);
        }
        return netX;
    }
    public double calcNetForceExertedByY(Body[] allBodys){
        double netY=0;
        for (Body b:allBodys) {
            if(this.equals(b)){continue;}
            netY+=this.calcForceExertedByY(b);
        }
        return netY;
    }
    public void update(double time,double xForce,double yForce){
        double dxVel=xForce/this.mass;
        double dyVel=yForce/this.mass;
        this.xxVel+=time*dxVel;
        this.yyVel+=time*dyVel;
        this.xxPos+=time*this.xxVel;
        this.yyPos+=time*this.yyVel;
    }
    public void draw(){
        StdDraw.picture(this.xxPos, this.yyPos, "./images/"+this.imgFileName);
    }
}
