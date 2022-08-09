package main;

import java.util.Random;

// Recommendation ITU-R P.1411

public class P1411{
    // Class implementation of Recommendation ITU-R P.1411-11
    //
    // THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND,
    // EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF
    // MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT.
    // IN NO EVENT SHALL THE AUTHORS BE LIABLE FOR ANY CLAIM, DAMAGES OR
    // OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE,
    // ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR
    // OTHER DEALINGS IN THE SOFTWARE.
    //
    // You may improve, modify, and create derivative works of the software or
    // any portion of the software, and you may copy and distribute such
    // modifications or works. Modified works should carry a notice stating
    // that you changed the software and should note the date and nature of
    // any such change.
    //
    // Please provide appropriate acknowledgments in any copies or
    // derivative works of this software.

    public enum ClutterEnvironment {

        NONE("No clutter"),
        WATER("Water/Sea"),
        URBAN("Urban"),
        URBAN_MICRO("Urban Micro Cell"),
        SUBURBAN("Suburban"),
        DENSE_SUBURBAN("Dense Suburban"),
        RURAL("Rural"),
        DENSE_URBAN("Dense Urban"),
        HIGH_RISE_URBAN("High-rise Urban"),
        RESIDENTIAL("Residential"),
        INDUSTRIAL("Industrial zone"),
        USER_SPECIFIED("User specified");
        private String name;
        ClutterEnvironment(String name) {
            this.name = name;
        }

        @Override
        public String toString() {
            return name;
        }

    }

    public double getGaussianDistribution(double mu, double sigma){

        Random x = new Random();

        return x.nextGaussian()*sigma + mu;
    }

    public double pl_1411_belowroof(double f, double d, ClutterEnvironment env, boolean variations) {

        //pl_1411_belowroof: path loss according to P.1411-11 §4.1.1
        //   L = pl_1411_belowroof(f, d, type, variations)
        //
        //   This function computes the path loss as defined in ITU-R P.1411-11
        //   (Section 4.1.1: Site-general model),
        //   where both the Tx and Rx stations are located below-rooftop,
        //   regardless of their antenna heights
        //
        //     Input parameters:
        //     f       -   Frequency (GHz): 0.8 - 82
        //     d       -   3D direct distance between the Tx and Rx stations (m)
        //     env    -   Environment type, according to Table 4:
        //                 1 - Urban high-rise, Urban low-rise/Suburban, LoS
        //                     0.8 <= f <= 82, 5 < d < 660
        //                 2 - Urban high-rise, NLoS
        //                     0.8 <= f <= 82, 30 < d < 715
        //                 3 - Urban low-rise/Suburban, NLOS
        //                     10 <= f <= 73, 30 < d < 250
        //                 4 - Residential, NLOS
        //                     0.8 <= f <= 73, 30 < d < 170
        //     variations - true if variations are to be computed in Monte-Carlo analysis

        //
        //     Output parameters:
        //     L     -   basic transmission loss according to P.1411-11 §4.1.1
        //
        //     Example:
        //     L = pl_1411_belowroof(f, d, type, variations)

        //     Rev   Date        Author                          Description
        //     -------------------------------------------------------------------------------
        //     v0    02MAY17     Ivica Stevanovic, OFCOM         Initial version
        //     v1    25NOV19     Ivica Stevanovic, OFCOM         Aligned with Recommendation (included variations instead of percentage of locations)
        //     v2    26NOV19     Ivica Stevanovic, OFCOM         Aligned with P.1411-10
        //     v3    26MAY21     Ivica Stevanovic, OFCOM         included FS loss for d<dmin
        //     v4    17JUL21     Ivica Stevanovic, OFCOM         aligned with P.1411-11 (upper frequency limit)

        double alpha = 0;
        double beta = 0;
        double gamma = 0;
        double sigma = 0;

        // Clutter height of the receiver
        if (env == ClutterEnvironment.URBAN) {
            //Lines below commented to avoid runtime errors
            /*    if (f < 0.8  || f > 73 ) {
                throw new RuntimeException("Frequency is outside the valid domain [0.8, 73] GHz");
            }
            if (d < 5 || d > 660) {
                throw new RuntimeException("3D distance between Tx and Rx is outside the valid domain [5, 660] m");
            }*/

            alpha = 2.12;
            beta = 29.2;
            gamma = 2.11;
            sigma = 5.06;


        } else if (env == ClutterEnvironment.HIGH_RISE_URBAN) {

            //Lines below commented to avoid runtime errors
            /* if (f < 0.8 || f > 38 ) {
                throw new RuntimeException("Frequency is outside the valid domain [0.8, 38] GHz");
            }
            if (d < 30 || d > 715) {
                throw new RuntimeException("3D distance between Tx and Rx is outside the valid domain [30, 715] m");
            }*/

            alpha = 4.00;
            beta = 10.2;
            gamma = 2.36;
            sigma = 7.6;

        } else if (env == ClutterEnvironment.SUBURBAN) {

            //Lines below commented to avoid runtime errors
            /*if (f < 10  || f > 73 ) {
                throw new RuntimeException("Frequency is outside the valid domain [10, 73] GHz");
            }
            if (d < 30 || d > 250) {
                throw new RuntimeException("3D distance between Tx and Rx is outside the valid domain [30, 250] m");
            }*/

            alpha = 5.06;
            beta = -4.68;
            gamma = 2.02;
            sigma = 9.33;

        } else if (env == ClutterEnvironment.RESIDENTIAL) {

            //Lines below commented to avoid runtime errors
                                        /*if (f < 0.8  || f > 73 ) {
                throw new RuntimeException("Frequency is outside the valid domain [0.8, 73] GHz");
            }
            if (d < 30 || d > 170) {
                throw new RuntimeException("3D distance between Tx and Rx is outside the valid domain [30, 170] m");
            }*/

            alpha = 3.01;
            beta = 18.8;
            gamma = 2.07;
            sigma = 3.07;

        }


        double L = 10 * alpha * Math.log10(d) + beta + 10 * gamma * Math.log10(f);

        // for d < dmin, use median value of Free-space if L < L_freespace
        if (env == ClutterEnvironment.URBAN)
        {
            if (d < 5) {
                double Lfs = 20.0 * Math.log10(4e9 * Math.PI * d * f / 299792458);
                L = Math.max(Lfs, L);
            }
        } else {
            if (d < 30) {
                double Lfs = 20.0 * Math.log10(4e9 * Math.PI * d * f / 299792458);
                L = Math.max(Lfs, L);
            }
        }

        if (variations) {

            if (env == ClutterEnvironment.HIGH_RISE_URBAN || env == ClutterEnvironment.SUBURBAN) { // NLOS Urban high-rise or NLOS Urban low-rise and Suburban

                // add standard deviation with capping so that the excess path loss is never negative

                double Lfs = 20.0 * Math.log10(4e9 * Math.PI * d * f / 299792458);

                double mu = L - Lfs;

                double A = getGaussianDistribution(mu, sigma);

                L = 10.0 * Math.log10(Math.pow(10, 0.1 * A) + 1.0) + Lfs;

            } else {

                double std = getGaussianDistribution(0, sigma);
                L = L + std;

            }

        }


        return L;

    }

    public double pl_1411_aboveroof(double f, double d, ClutterEnvironment env, boolean variations) {

        //pl_1411_aboveroof: path loss according to P.1411-11 §4.2.1
        //   L = pl_1411_aboveroof(f, d, type, variations)
        //
        //   This function computes the path loss as defined in ITU-R P.1411-11
        //   (Section 4.2.1: Site-general model),
        //   where one of the stations is located below-rooftop, and the other above-rooftop
        //   regardless of their antenna heights
        //
        //
        //     Input parameters:
        //     f       -   Frequency (GHz): 2.2 - 73
        //     d       -   3D direct distance between the Tx and Rx stations (m)
        //     env    -   Environment type, according to Table 8:
        //                 1 - Urban high-rise, Urban low-rise/Suburban, LoS
        //                     2.2 <= f <= 73, 55 < d < 1200
        //                 2 - Urban high-rise, NLoS
        //                     2.2 <= f <= 66.5, 260 < d < 1200
        //     variations - true if variations are to be computed in Monte-Carlo analysis
        //
        //     Output parameters:
        //     L     -   basic transmission loss according to P.1411-11 §4.2.1
        //
        //     Example:
        //     L = pl_1411_aboveroof(f, d, type, variations)
        //
        //     Rev   Date        Author                          Description
        //     -------------------------------------------------------------------------------
        //     v0    02MAY17     Ivica Stevanovic, OFCOM         Initial version
        //     v3    25NOV19     Ivica Stevanovic, OFCOM         Aligned with Recommendation (included variations instead of percentage of locations)
        //     v4    26MAY21     Ivica Stevanovic, OFCOM         included FS loss for d<dmin

        double alpha = 0;
        double beta = 0;
        double gamma = 0;
        double sigma = 0;

        // Clutter height of the receiver
        if (env == ClutterEnvironment.URBAN) {
            //Lines below commented to avoid runtime errors
            /* if (f < 2.2  || f > 73 ) {
                throw new RuntimeException("Frequency is outside the valid domain [2.2, 73] GHz");
            }
            if (d < 55 || d > 1200) {
                throw new RuntimeException("3D distance between Tx and Rx is outside the valid domain [55, 1200] m");
            }*/

            alpha = 2.29;
            beta = 28.6;
            gamma = 1.96;
            sigma = 3.48;

        } else if (env == ClutterEnvironment.HIGH_RISE_URBAN) {

            //Lines below commented to avoid runtime errors
            /*if (f < 2.2  || f > 66.5 ) {
                throw new RuntimeException("Frequency is outside the valid domain [2.2, 66.5] GHz");
            }
            if (d < 260 || d > 1200) {
                throw new RuntimeException("3D distance between Tx and Rx is outside the valid domain [260, 1200] m");
            }*/

            alpha = 4.39;
            beta = -6.27;
            gamma = 2.30;
            sigma = 6.89;

        }


        double L = 10 * alpha * Math.log10(d) + beta + 10 * gamma * Math.log10(f);

        // for d < dmin, use median value of Free-space if L < L_freespace
        if (env == ClutterEnvironment.URBAN) {
            if (d < 55) {
                double Lfs = 20.0 * Math.log10(4e9 * Math.PI * d * f / 299792458);
                L = Math.max(L, Lfs);
            }
        } else {
            if (d < 260) {
                double Lfs = 20.0 * Math.log10(4e9 * Math.PI * d * f / 299792458);
                L = Math.max(L, Lfs);
            }
        }

        if (variations) {
            double std = getGaussianDistribution(0, sigma);;
            L = L + std;
        }


        return L;

    }

    public double pl_1411_lowheight(double fGHz, double dm, ClutterEnvironment env, double p, double w) {

        //function L = pl_1411_lowheight(fMHz, dm, type, p, w)
        //pl_1411_lowheight: path loss according to P.1411-11 §4.3.1
        //
        //   This function computes the path loss as defined in ITU-R P.1411-11
        //   (Section 4.3.1: Site-general model) not exceeded for probability p,
        //   between two terminals of low hight from below roof-top to near street
        //   level (1.9 m < h < 3 m).
        //
        //     Input parameters:
        //     fGHz    -   Frequency (GHz): 0.3 - 3
        //     dm      -   3D direct distance between the Tx and Rx stations (m)
        //     env    -   Environment type:
        //                 1 - suburban
        //                 2 - urban
        //                 3 - dense urban/hire-rise
        //     p       -   location percentage (%): 0 - 100
        //
        //     Output parameters:
        //     L     -   Path loss not exceeded for p% according to P.1411-11 §4.3.1
        //
        //     Example:
        //     L = pl_1411_lowheight(fGHz, dm, type, p,w )
        //
        //     Rev   Date        Author                          Description
        //     -------------------------------------------------------------------------------
        //     v0    16JAN18     Ivica Stevanovic, OFCOM         Initial version

        // Checking passed parameter to the defined limits

        if (p <= 0 || p >= 100) {
            throw new RuntimeException("Location percentage is outside of the valid domain (0, 100) %");
        }

        double Lurban = 0;

        if (env == ClutterEnvironment.SUBURBAN) {
            Lurban = 0;
        } else if (env == ClutterEnvironment.URBAN) {
            Lurban = 6.8;
        } else if (env == ClutterEnvironment.DENSE_URBAN) {
            Lurban = 2.3;
        } else {
            throw new RuntimeException("Wrong value in the environmental variable type.");
        }
        double fMHz = fGHz*1000;

        // Step 1: Calculate the median value of the line-of-sight loss (Eq 58)

        double LLoSMedian = 32.45 + 20 * Math.log10(fMHz) + 20 * Math.log10(dm / 1000);

        // Step 2: For the required location percentage, calulate the LoS location
        // correction (Eq 59)

        double sigma = 7;
        double DeltaLLoS = 1.5624 * sigma * (Math.sqrt(-2.0 * Math.log(1.0 - p / 100)) - 1.1774);

        // Step 3: Add the LoS location correction to the median value of LoS loss
        // (Eq. 60);

        double LLoS = LLoSMedian + DeltaLLoS;

        // Step 4: Calculate the median value of the NLos loss (Eq. 61)

        double LNLoSMedian = 9.5 + 45 * Math.log10(fMHz) + 40 * Math.log10(dm / 1000) + Lurban;

        // Step 5: For the required location percentage p (%) add the NLoS location
        // correction (Eq. 62)

        double DeltaLNLoS = norminv(p / 100, 0, sigma);

        // Add the NLoS location correction to the median value of NLoS loss (Eq. 63)

        double LNLoS = LNLoSMedian + DeltaLNLoS;

        // Step 7: For the required location percentage calculate the distance dLos
        // for which the LoS fraction FLoS equals p (Eq. 64)

        double dLoS = 0;
        if (p < 45) {
            dLoS = 212 * Math.pow((Math.log10(p / 100)), 2) - 64 * Math.log10(p / 100);
        } else {
            dLoS = 79.2 - 70 * (p / 100);
        }

        // Step 8: Calculate the pathloss

        double L;

        if (dm < dLoS) {

            L = LLoS;

        } else if (dm > dLoS + w) {

            L = LNLoS;

        } else {

            // Step 1: Calculate the median value of the line-of-sight loss (Eq 58)

            LLoSMedian = 32.45 + 20 * Math.log10(fMHz) + 20 * Math.log10(dLoS / 1000);

            // Step 3: Add the LoS location correction to the median value of LoS loss (Eq. 60);

            LLoS = LLoSMedian + DeltaLLoS;

            // Step 4: Calculate the median value of the NLos loss (Eq. 61)

            LNLoSMedian = 9.5 + 45 * Math.log10(fMHz) + 40 * Math.log10((dLoS + w) / 1000) + Lurban;

            // Add the NLoS location correction to the median value of NLoS loss (Eq. 63)

            LNLoS = LNLoSMedian + DeltaLNLoS;

            L = LLoS + (LNLoS - LLoS) * (dm - dLoS) / w;

        }

        return L;
    }


    private double norminv(double p, double mu, double sigma) {
        //   This function computes the inverse of the normal distribution with mean mu and standard deviation sigma
        //
        //     Input parameters:
        //     p       -   percentage of locations (0-1)
        //     mu      -   mean of the normal distribution (dB)
        //     sigma   -   standard deviation of the normal distribution (dB)
        //
        //     Output parameters:
        //     y       -   value for which

        //
        //
        //     Rev   Date        Author                          Description
        //     -------------------------------------------------------------------------------
        //     v0    03MAY17     Ivica Stevanovic, OFCOM         Initial version in Java


        double y;

        y = mu + sigma* Qi(1-p);

        return y;
    }

    private double Qi(double x) {
        //Anex 5, Sec. 16 An approximation to the inverse complementary cumulative normal distribution
        // function
        // Rev     Date    Author                      Description
        // -------------------------------------------------------------------------------
        // v1      1DEC16  Ivica Stevanovic, OFCOM     Initial version

        double out;

        if (x <= .5) {
            out = T(x) - C(x);          //(39 a)
        } else {
            out = -(T(1 - x) - C(1 - x)); //(39 b)
        }

        return out;

    }

    private double T(double y) {
        double outT = Math.sqrt(-2 * Math.log(y));     //(39 c)
        return outT;
    }

    private double C(double z) {
        double C0 = 2.515517;
        double C1 = 0.802853;
        double C2 = 0.010328;
        double D1 = 1.432788;
        double D2 = 0.189269;
        double D3 = 0.001308;
        double outC = (((C2 * T(z) + C1) * T(z)) + C0) / (((D3 * T(z) + D2) * T(z) + D1) * T(z) + 1);//(39d)
        return outC;
    }

}
