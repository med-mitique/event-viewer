package extends_classes;
import java.awt.Color;


@SuppressWarnings("serial")
public class MyColor extends Color{
	
	public static final Color DarkGray = new Color(24, 25,31, 250);
	public static final Color violet = new Color(48,25,60);
	public static final Color dark_violet = new Color(48,25,52);
	public static final Color copaque = new Color(0, 0, 0, 0);
	public static final Color gray_light = new Color(43, 45,47, 200);
	public static final Color arc1 = new Color(47, 86, 233);
	public static final Color arc2 = new Color(47, 141, 245);
	public static final Color arc3 = new Color(51,171,249);
	public static final Color bar1 = new Color(60,157,183);
	public static final Color bar3 = new Color(50,83,223);
	public static final Color bar2 = new Color(0,80,100);
	
	public MyColor(float r, float g, float b) {
		super(r, g, b);
	}

	
}
