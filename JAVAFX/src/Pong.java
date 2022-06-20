import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.Cursor;
import javafx.scene.Group;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class Pong extends Application
{
    double x=450;
    double y=950;
    int speedx=4;
    int speedy=3;
    boolean smerx=true;
    boolean smery=true;
    int xr=1000;
    int yr=1000;
    int xz=500;
    int yz=500;
    int tocke=0;

    Text t=new Text();

    Circle zoga=new Circle();

    Rectangle igralec=new Rectangle();



    private void update()
    {
        if (smerx==true)
        {
            xz+=speedx;
            zoga.setCenterX(xz);
        }

        if(smerx==false)
        {
            xz-=speedx;
            zoga.setCenterX(xz);
        }

        if(smery==true)
        {
            yz+=speedy;
            zoga.setCenterY(yz);
        }

        if(smery==false)
        {
            yz-=speedy;
            zoga.setCenterY(yz);
        }

        if(xz<15)
        {
            smerx=true;
        }

        if(xz>xr-15)
        {
            smerx=false;
        }
        
        if(yz<15)
        {
            smery=true;
        }

        if(yz>yr-15)
        {
            tocke=0;
            smery=false;
            t.setText(tocke+"");
        }

        if(xz>x-360 && xz<x-260 && yz<1000 && yz>935)
        {
            smery=false;
            tocke++;
            t.setText(tocke+"");
        }

        System.out.println(x);
    }

    @Override
    public void start(Stage stage) throws Exception 
    {
        Pane pane=new Pane();

        t.setText(tocke+"");
        t.setX(100);
        t.setY(100);
        t.setFill(Color.BLACK);
        t.setFont(Font.font("Comic Sans",50));

        zoga.setCenterX(xz);
        zoga.setCenterY(yz);
        zoga.setRadius(20);
        zoga.setFill(Color.RED);

        igralec.setX(x);
        igralec.setY(y);
        igralec.setWidth(100);
        igralec.setHeight(25);
        igralec.setFill(Color.BLACK);

        Scene scene=new Scene(pane, xr, yr, Color.GRAY);
        stage.setTitle("ping pong");
        stage.setScene(scene);
        stage.show();

        pane.getChildren().add(igralec);
        pane.getChildren().add(zoga);
        pane.getChildren().add(t);
        pane.setOnMouseMoved(new EventHandler<MouseEvent>() 
        {
        @Override
        public void handle(MouseEvent event) 
        {
            x=event.getScreenX()-150;
            igralec.setX(x-360);
            System.out.println(event.getScreenX());
            if (x<360)
            {
                x=0;
                igralec.setX(x);
            }
            if (x>1259)
            {
                x=909;
                igralec.setX(x);
            }
        }
        });

        AnimationTimer timer=new AnimationTimer() 
        {
            @Override
            public void handle(long now) 
            {
                update();     
            }
        };

        timer.start();
    }
    public static void main(String[] args) 
    {
        Application.launch(args);
    }
}
