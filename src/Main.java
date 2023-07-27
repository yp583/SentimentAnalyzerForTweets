import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        SentimentAnalyzer sa = new SentimentAnalyzer("largerdata.csv");
        sa.TrainSentiment();
        System.out.println(sa.calcSentiment("The shockwaves are still being felt by the incredible Western sanctions that have rendered the $630 billion in reserves the Russian central bank accumulated virtually unusable. Can the current dollar-centered global financial system last if money can be summarily cancelled?\n" +
                "\n" +
                "Arthur Hayes, a former emerging markets trader and co-founder of the BitMEX trading platform, argues central banks will choose, instead of dollars, to load up on either gold, storable grains like wheat, or storable commodities like oil and copper. “In essence, the largest surplus countries’ fiat currencies will implicitly grow their gold or commodity backing,” he writes, saying gold could rise beyond $10,000 per ounce.\n" +
                "\n" +
                "Luke Gromen, publisher of Forest For The Trees and a long-time dollar bear, said that shift had been happening even before the sanctions. In a podcast with Grant Williams, Gromen said that over the last eight years, global central banks have bought about $260 billion worth of gold, compared to $60 billion in Treasurys. “So there’s been this very slow, but steady and recently accelerating move toward the away from this dollar system that broke in 2005, through 2008 to this system that looks a lot like what was proposed by [John Maynard] Keynes 80 years ago,” he says.\n" +
                "\n" +
                "The dollar-centered system has some disadvantages for the U.S. “The issue with this is the American version of this deal that we’re printing dollars for oil as we have since ‘73 is, and this is the downside of the deal, is you got to run the deficits to supply the dollars to the world,” he said. “Which means you got to offshore all the manufacturing. You got to offshore all the manufacturing jobs. You got to run a bunch of deficits at the government level. You got to do all these things that are really, really good for GDP growth and the economy in the short and medium term. And in the long run, they bankrupt you.”\n" +
                "\n" +
                "Gromen, like Hayes, expects more gold accumulation. “So every central bank in the world is now looking at this thinking, okay, we need to not be in a position where that can happen to us. Because who knows what might happen in the future and what might get us deemed a bad actor. So presumably they are going to be looking to accumulate a lot more gold,” he said.\n" +
                "\n" +
                "(It should be noted that gold has its perils for foreign central banks. In Russia’s case in particular, the central bank won’t be able to sell to any western entity directly, and bipartisan legislation introduced in the U.S. Senate would impose secondary sanctions to any American entities knowingly transacting with or transporting gold from Russia.)\n" +
                "\n" +
                "Gromen expects the end of the 40-year bull market in bonds. And he sees the potential for re-industrialization. “When you see Ohio getting an Intel INTC, +0.71% fab and the CEO of Intel saying, ‘We’re going to make Ohio one of the biggest Intel manufacturing regions in the world.’ What? Ohio was ground zero of the people who took it in the shorts from 1973 to present under this deal. Another semi fab in Arizona, another semi fab in Texas,” says Gromen. “It’s not even the first inning in this reindustrialization of America, but reindustrialization was never going to happen until you changed this dollar system and removed treasuries as the primary reserve asset, replaced it with a neutral one. And here we are. We’re two weeks into it. It’s incredibly exciting."));

        NDimensionalAnalyzer na = new NDimensionalAnalyzer("largerdata.csv");
        na.getPoints();
        System.out.println(na.calcSentiment("i love you", 10));
    }
}
