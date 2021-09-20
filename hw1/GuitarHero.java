/** A client that uses the synthesizer package to replicate a plucked guitar string sound */
import es.datastructur.synthesizer.GuitarString;

public class GuitarHero {

    private static final String theKey="q2we4r5ty7u8i9op-[=zxdcfvgbnjmk,.;/' ";
    // private static int lengthOfString=theKey.length();
    private static final GuitarString[] theConcert=new GuitarString[theKey.length()];
    static{
        // If you don't want to use static code blocks, you can put them in main function instead.
        for(int i = 0; i <theKey.length(); i++){
            double aConcert=440.0* Math.pow(2, (i-24.0) / 12.0);
            theConcert[i] =new GuitarString(aConcert);
        }
    }

    public static void main(String[] args) {
        System.out.println("Type 1 to start/stop a damper!");
        boolean damper= false;

        while (true) {
            // check if the user has typed a key; if so, process it
            if (StdDraw.hasNextKeyTyped()) {
                char key = StdDraw.nextKeyTyped();
                if (key == '1'){
                    damper=!damper;
                    System.out.println("damper "+(damper?"start":"stop"));
                }
                int keyIndex = theKey.indexOf(key);
                if(keyIndex!=-1){
                    theConcert[keyIndex].pluck();
                }
            }

            // compute the superposition of samples
            double sample=0;
            for (GuitarString aString : theConcert) {
                sample+=aString.sample();
            }

            // play the sample on standard audio
            StdAudio.play(sample);

            // advance the simulation of each guitar string by one step
            for (GuitarString aString : theConcert) {
                aString.tic(damper);
            }
        }
    }
}
