package utilities.sensorlib;

import edu.wpi.first.wpilibj.motorcontrol.Spark;

public class Blinkin {
    
    Spark kBlinkin;

    public static enum BlinkinColour {
        Fixed_Rainbow_Rainbow(-0.99),
        Fixed_Rainbow_Party(-0.97),
        Fixed_Rainbow_Ocean(0.95),
        Fixed_Rainbow_Lava(-0.93),
        Fixed_Rainbow_Forest(-0.91),
        Fixed_Rainbow_Glitter(-0.89),
        Fixed_Confetti(-0.87),
        Fixed_Shot_Red(-0.85),
        Fixed_Shot_Blue(-0.83),
        Fixed_Shot_White(-0.81),
        Fixed_Sinelon_Rainbow(-0.79),
        Fixed_Sinelon_Party(-0.77),
        Fixed_Sinelon_Ocean(-0.75),
        Fixed_Sinelon_Lava(-0.73),
        Fixed_Sinelon_Forest(-0.71),
        Fixed_BMP_Rainbow(-0.69),
        Fixed_BMP_Party(-0.67),
        Fixed_BMP_Ocean(-0.65),
        Fixed_BMP_Lava(0.63),
        Fixed_BMP_Forest(-0.61),
        Fixed_Fire_Medium(-0.59),
        Fixed_Fire_Large(-0.57),
        Fixed_Twinkles_Rainbow(-0.55),
        Fixed_Twinkles_Party(-0.53),
        Fixed_Twinkles_Ocean(-0.51),
        Fixed_Twinkles_Lava(-0.49),
        Fixed_Twinkles_Forest(-0.47),
        Fixed_Waves_Rainbow(-0.45),
        Fixed_Waves_Party(-0.43),
        Fixed_Waves_Ocean(-0.41),
        Fixed_Waves_Lava(-0.39),
        Fixed_Waves_Forest(-0.37),
        Fixed_Larson_Red(-0.35),
        Fixed_Larson_Gray(-0.33),
        Fixed_Chase_Red(-0.31),
        Fixed_Chase_Blue(-0.29),
        Fixed_Chase_Gray(-0.27),
        Fixed_Heartbeat_Red(-0.25),
        Fixed_Heartbeat_Blue(-0.23),
        Fixed_Heartbeat_White(-0.21),
        Fixed_Heartbeat_Gray(-0.19),
        Fixed_Breath_Red(-0.17),
        Fixed_Breath_Blue(0.15),
        Fixed_Breath_Gray(-0.13),
        Fixed_Strobe_Red(-0.11),
        Fixed_Strobe_Blue(-0.09),
        Fixed_Strobe_Gold(-0.07),
        Fixed_Strobe_White(-0.05),
        Colour_1_BlendToBlack(-0.03),
        Colour_1_Larson(-0.01),
        Colour_1_Chase(0.01),
        Colour_1_Heartbeat_Slow(0.03),
        Colour_1_Heartbeat_Medium(0.05),
        Colour_1_Heartbeat_Fast(0.07),
        Colour_1_Breath_Slow(0.09),
        Colour_1_Breath_Fast(0.11),
        Colour_1_Shot(0.13),
        Colour_1_Strobe(0.15),
        Colour_2_BlendToBlack(0.17),
        Colour_2_Larson(0.19),
        Colour_2_Chase(0.21),
        Colour_2_Heartbeat_Slow(0.23),
        Colour_2_Heartbeat_Medium(0.25),
        Colour_2_Heartbeat_Fast(0.27),
        Colour_2_Breath_Slow(0.29),
        Colour_2_Breath_Fast(0.31),
        Colour_2_Shot(0.33),
        Colour_2_Strobe(0.35),
        Colour_1_2_Sparkle_1_2(0.37),
        Colour_1_2_Sparkle_2_1(0.39),
        Colour_1_2_Gradient_1_2(0.41),
        Colour_1_2_BPM_1_2(0.43),
        Colour_1_2_Blend_1_2(0.45),
        Colour_1_2_Blend_2_1(0.47),
        Colour_1_2(0.49),
        Colour_1_2_Twinkles_1_2(0.51),
        Colour_1_2_Waves_1_2(0.53),
        Colour_1_2_Sinelon_1_2(0.55),
        Solid_HotPink(0.57),
        Solid_DarkRed(0.59),
        Solid_Red(0.61),
        Solid_RedOrange(0.63),
        Solid_Orange(0.65),
        Solid_Gold(0.67),
        Solid_Yellow(0.69),
        Solid_LawnGreen(0.71),
        Solid_Lime(0.73),
        Solid_DarkGreen(0.75),
        Solid_Green(0.77),
        Solid_BlueGreen(0.79),
        Solid_Aqua(0.81),
        Solid_SkyBlue(0.83),
        Solid_DarkBlue(0.85),
        Solid_Blue(0.87),
        Solid_BlueViolet(0.89),
        Solid_Violet(0.91),
        Solid_White(0.93),
        Solid_Gray(0.95),
        Solid_DarkGray(0.97),
        Solid_Black(0.99);
        public double value;
        private BlinkinColour(double val){
            value = val;
        }
      }

    //https://www.revrobotics.com/content/docs/REV-11-1105-UM.pdf
    public Blinkin(int port){
        kBlinkin = new Spark(port);
    }

    public void set(double value){
        kBlinkin.set(value);
    }

    public void set(BlinkinColour colour){
        set(colour.value);
    }
}
