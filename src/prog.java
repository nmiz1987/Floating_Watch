/*
 * My organization installed everyone floating watch on the screen.
 * I thought it would be nice to build one myself.
 * Nice use of animation.
 */

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class prog extends Application {
	
	public static void main(String[] args) {
		launch(args);
	}

	public void start(Stage startStage) {
		ClockPane clock = new ClockPane();
		clock.setAlignment(Pos.CENTER);
		EventHandler<ActionEvent> eventHandler = e -> {
			clock.setCurrentTime(); // Set a new clock time
		};
		// Create an animation for a running clock
		Timeline animation = new Timeline(new KeyFrame(Duration.millis(1000), eventHandler));
		animation.setCycleCount(Timeline.INDEFINITE);
		animation.play();

		Scene scene = new Scene(clock, 150, 60);
		Stage stage = new Stage();
		stage.setScene(scene);
		stage.setFullScreen(false);
		stage.setResizable(false);
		stage.show();
		stage.setAlwaysOnTop(true);
	}

	public class ClockPane extends VBox {
		private int hour;
		private int minute;
		private int second;
		private int day;
		private int month;
		private int year;

		public ClockPane() {
			setCurrentTime();
		}

		public void setCurrentTime() {
			GregorianCalendar cal = new GregorianCalendar();
			this.hour = cal.get(Calendar.HOUR_OF_DAY);
			this.minute = cal.get(Calendar.MINUTE);
			this.second = cal.get(Calendar.SECOND);

			this.day = cal.get(Calendar.DAY_OF_MONTH);
			this.month = cal.get(Calendar.MONTH) + 1;
			this.year = cal.get(Calendar.YEAR);

			Label l1 = new Label("השעה עכשיו היא:");
			l1.setFont(Font.font("Tahoma", FontWeight.LIGHT, 15));
			
//			l1.centerShapeProperty().bind(VBox.widthProperty().divide(2));
			
			String time = checkNum(hour) + ":" + checkNum(minute) + ":" + checkNum(second);
			Text l2 = new Text(time);
			l2.setFont(Font.font("Consolas", FontWeight.BOLD, 17));

			String date = checkNum(day) + "/" + checkNum(month) + "/" + year;
			Text l3 = new Text(date);
			l3.setFont(Font.font("Consolas", FontWeight.BOLD, 15));

			getChildren().clear();
			getChildren().addAll(l1, l2, l3);
		}

		public String checkNum(int num) {
			if (num < 10)
				return "0" + num;
			else
				return "" + num;
		}
	}


}
