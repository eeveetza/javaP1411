package test;

import main.P1411;

import org.junit.Before;
import org.junit.Test;


public class P1411Test {
    // the results are compared to the MATLAB implementation of Recommendation ITU-R P.1411-11
    // the test is passed when the results for transmission loss are within 0.01 dB of difference
    // different location percentages, frequencies, distances, and environment categories are tested
    //     Rev   Date        Author                          Description
    //     -------------------------------------------------------------------------------
    //     v0    03MAY17     Ivica Stevanovic, OFCOM         Initial version in Java
    //     v1    19JAN18     Ivica Stevanovic, OFCOM         Introduced tests for §4.3.1
    //     v2    25NOV19     Ivica Stevanovic, OFCOM         Modified tests for §4.1.1 and §4.2.1 to align with modifications in the corresponding functions
    //     v3    26NOV19     Ivica Stevanovic, OFCOM         Introduced new tests for P.1411-10 §4.1.1.
    //     v4    17JUL21     Ivica Stevanovic, OFCOM         site general models in P.1411-11 are the same as in P.1411-10 except for an extension of upper frequency limit in §4.1.1
    //



    TestUtil util;

    @Before
    public void setup() {

        util = new TestUtil( 0.01 );
    }


    @Test
    public void test1() {
        P1411 calculator = new P1411();
        double[] f = new double[4];
        double[] d = new double[5];
        d[0] = 5.000000;
        d[1] = 50.000000;
        d[2] = 100.000000;
        d[3] = 200.000000;
        d[4] = 660.000000;
        f[0] = 0.800000;
        f[1] = 10.000000;
        f[2] = 50.000000;
        f[3] = 73.000000;
        P1411.ClutterEnvironment type = P1411.ClutterEnvironment.URBAN;
        double[] expectedResult = new double[20];
        expectedResult[0] = 41.973363;
        expectedResult[1] = 63.173363;
        expectedResult[2] = 69.555199;
        expectedResult[3] = 75.937035;
        expectedResult[4] = 86.929530;
        expectedResult[5] = 65.118164;
        expectedResult[6] = 86.318164;
        expectedResult[7] = 92.700000;
        expectedResult[8] = 99.081836;
        expectedResult[9] = 110.074331;
        expectedResult[10] = 79.866431;
        expectedResult[11] = 101.066431;
        expectedResult[12] = 107.448267;
        expectedResult[13] = 113.830103;
        expectedResult[14] = 124.822599;
        expectedResult[15] = 83.334276;
        expectedResult[16] = 104.534276;
        expectedResult[17] = 110.916112;
        expectedResult[18] = 117.297948;
        expectedResult[19] = 128.290444;
        int count = 0;
        for (int fi = 0; fi < 4; fi++) {
            for (int di = 0; di < 5; di++) {
                double result = calculator.pl_1411_belowroof(f[fi], d[di], type, false);
                util.assertDoubleEquals(expectedResult[count], result);
                count = count + 1;
            }
        }
    }
    @Test
    public void test2() {
        P1411 calculator = new P1411();
        double[] f = new double[4];
        double[] d = new double[5];
        d[0] = 30.000000;
        d[1] = 100.000000;
        d[2] = 200.000000;
        d[3] = 660.000000;
        d[4] = 715.000000;
        f[0] = 0.800000;
        f[1] = 10.000000;
        f[2] = 20.000000;
        f[3] = 38.000000;
        P1411.ClutterEnvironment type = P1411.ClutterEnvironment.HIGH_RISE_URBAN;
        double[] expectedResult = new double[20];
        expectedResult[0] = 66.997774;
        expectedResult[1] = 87.912924;
        expectedResult[2] = 99.954124;
        expectedResult[3] = 120.694681;
        expectedResult[4] = 122.085165;
        expectedResult[5] = 92.884850;
        expectedResult[6] = 113.800000;
        expectedResult[7] = 125.841200;
        expectedResult[8] = 146.581757;
        expectedResult[9] = 147.972242;
        expectedResult[10] = 99.989158;
        expectedResult[11] = 120.904308;
        expectedResult[12] = 132.945508;
        expectedResult[13] = 153.686065;
        expectedResult[14] = 155.076550;
        expectedResult[15] = 106.567743;
        expectedResult[16] = 127.482893;
        expectedResult[17] = 139.524093;
        expectedResult[18] = 160.264650;
        expectedResult[19] = 161.655135;
        int count = 0;
        for (int fi = 0; fi < 4; fi++) {
            for (int di = 0; di < 5; di++) {
                double result = calculator.pl_1411_belowroof(f[fi], d[di], type, false);
                util.assertDoubleEquals(expectedResult[count], result);
                count = count + 1;
            }
        }
    }
    @Test
    public void test3() {
        P1411 calculator = new P1411();
        double[] f = new double[4];
        double[] d = new double[5];
        d[0] = 30.000000;
        d[1] = 50.000000;
        d[2] = 100.000000;
        d[3] = 200.000000;
        d[4] = 250.000000;
        f[0] = 10.000000;
        f[1] = 20.000000;
        f[2] = 38.000000;
        f[3] = 73.000000;
        P1411.ClutterEnvironment type = P1411.ClutterEnvironment.SUBURBAN;
        double[] expectedResult = new double[20];
        expectedResult[0] = 90.262335;
        expectedResult[1] = 101.487882;
        expectedResult[2] = 116.720000;
        expectedResult[3] = 131.952118;
        expectedResult[4] = 136.855764;
        expectedResult[5] = 96.343141;
        expectedResult[6] = 107.568688;
        expectedResult[7] = 122.800806;
        expectedResult[8] = 138.032924;
        expectedResult[9] = 142.936570;
        expectedResult[10] = 101.973964;
        expectedResult[11] = 113.199511;
        expectedResult[12] = 128.431629;
        expectedResult[13] = 143.663746;
        expectedResult[14] = 148.567393;
        expectedResult[15] = 107.701457;
        expectedResult[16] = 118.927004;
        expectedResult[17] = 134.159122;
        expectedResult[18] = 149.391240;
        expectedResult[19] = 154.294886;
        int count = 0;
        for (int fi = 0; fi < 4; fi++) {
            for (int di = 0; di < 5; di++) {
                double result = calculator.pl_1411_belowroof(f[fi], d[di], type, false);
                util.assertDoubleEquals(expectedResult[count], result);
                count = count + 1;
            }
        }
    }

    @Test
    public void test4() {
        P1411 calculator = new P1411();
        double[] f = new double[4];
        double[] d = new double[5];
        d[0] = 55.000000;
        d[1] = 100.000000;
        d[2] = 200.000000;
        d[3] = 660.000000;
        d[4] = 1200.000000;
        f[0] = 2.200000;
        f[1] = 10.000000;
        f[2] = 50.000000;
        f[3] = 73.000000;
        P1411.ClutterEnvironment type = P1411.ClutterEnvironment.URBAN;
        double[] expectedResult = new double[20];
        expectedResult[0] = 75.165790;
        expectedResult[1] = 81.111485;
        expectedResult[2] = 88.005071;
        expectedResult[3] = 99.879041;
        expectedResult[4] = 105.824735;
        expectedResult[5] = 88.054306;
        expectedResult[6] = 94.000000;
        expectedResult[7] = 100.893587;
        expectedResult[8] = 112.767556;
        expectedResult[9] = 118.713251;
        expectedResult[10] = 101.754118;
        expectedResult[11] = 107.699812;
        expectedResult[12] = 114.593399;
        expectedResult[13] = 126.467368;
        expectedResult[14] = 132.413063;
        expectedResult[15] = 104.975434;
        expectedResult[16] = 110.921128;
        expectedResult[17] = 117.814715;
        expectedResult[18] = 129.688684;
        expectedResult[19] = 135.634379;
        int count = 0;
        for (int fi = 0; fi < 4; fi++) {
            for (int di = 0; di < 5; di++) {
                double result = calculator.pl_1411_aboveroof(f[fi], d[di], type, false);
                util.assertDoubleEquals(expectedResult[count], result);
                count = count + 1;
            }
        }
    }
    @Test
    public void test5() {
        P1411 calculator = new P1411();
        double[] f = new double[5];
        double[] d = new double[5];
        d[0] = 360.000000;
        d[1] = 500.000000;
        d[2] = 660.000000;
        d[3] = 715.000000;
        d[4] = 1200.000000;
        f[0] = 2.200000;
        f[1] = 10.000000;
        f[2] = 20.000000;
        f[3] = 38.000000;
        f[4] = 66.500000;
        P1411.ClutterEnvironment type = P1411.ClutterEnvironment.HIGH_RISE_URBAN;
        double[] expectedResult = new double[25];
        expectedResult[0] = 113.827401;
        expectedResult[1] = 120.090505;
        expectedResult[2] = 125.383700;
        expectedResult[3] = 126.909757;
        expectedResult[4] = 136.781778;
        expectedResult[5] = 128.951680;
        expectedResult[6] = 135.214783;
        expectedResult[7] = 140.507979;
        expectedResult[8] = 142.034035;
        expectedResult[9] = 151.906057;
        expectedResult[10] = 135.875370;
        expectedResult[11] = 142.138473;
        expectedResult[12] = 147.431669;
        expectedResult[13] = 148.957725;
        expectedResult[14] = 158.829747;
        expectedResult[15] = 142.286703;
        expectedResult[16] = 148.549806;
        expectedResult[17] = 153.843001;
        expectedResult[18] = 155.369058;
        expectedResult[19] = 165.241079;
        expectedResult[20] = 147.876578;
        expectedResult[21] = 154.139681;
        expectedResult[22] = 159.432877;
        expectedResult[23] = 160.958933;
        expectedResult[24] = 170.830955;
        int count = 0;
        for (int fi = 0; fi < 5; fi++) {
            for (int di = 0; di < 5; di++) {
                double result = calculator.pl_1411_aboveroof(f[fi], d[di], type, false);
                util.assertDoubleEquals(expectedResult[count], result);
                count = count + 1;
            }
        }
    }

    @Test
    public void test6() {
        P1411 calculator = new P1411();
        double[] p = new double[7];
        double[] f = new double[4];
        double[] d = new double[5];
        double w = 20;
        p[0] = 0.100000;
        p[1] = 5.000000;
        p[2] = 10.000000;
        p[3] = 50.000000;
        p[4] = 70.000000;
        p[5] = 99.000000;
        p[6] = 99.100000;
        d[0] = 0.100000;
        d[1] = 10.000000;
        d[2] = 100.000000;
        d[3] = 1000.000000;
        d[4] = 3000.000000;
        f[0] = 0.300000;
        f[1] = 0.600000;
        f[2] = 1.200000;
        f[3] = 3.000000;
        P1411.ClutterEnvironment type = P1411.ClutterEnvironment.SUBURBAN;
        double[] expectedResult = new double[140];
        expectedResult[0] = -10.395332;
        expectedResult[1] = 29.604668;
        expectedResult[2] = 49.604668;
        expectedResult[3] = 69.604668;
        expectedResult[4] = 118.423681;
        expectedResult[5] = -4.374732;
        expectedResult[6] = 35.625268;
        expectedResult[7] = 55.625268;
        expectedResult[8] = 75.625268;
        expectedResult[9] = 131.970030;
        expectedResult[10] = 1.645868;
        expectedResult[11] = 41.645868;
        expectedResult[12] = 61.645868;
        expectedResult[13] = 81.645868;
        expectedResult[14] = 145.516380;
        expectedResult[15] = 9.604668;
        expectedResult[16] = 49.604668;
        expectedResult[17] = 69.604668;
        expectedResult[18] = 89.604668;
        expectedResult[19] = 163.423681;
        expectedResult[20] = -7.381600;
        expectedResult[21] = 32.618400;
        expectedResult[22] = 52.618400;
        expectedResult[23] = 109.456481;
        expectedResult[24] = 128.541331;
        expectedResult[25] = -1.361000;
        expectedResult[26] = 38.639000;
        expectedResult[27] = 58.639000;
        expectedResult[28] = 123.002831;
        expectedResult[29] = 142.087681;
        expectedResult[30] = 4.659600;
        expectedResult[31] = 44.659600;
        expectedResult[32] = 64.659600;
        expectedResult[33] = 136.549181;
        expectedResult[34] = 155.634031;
        expectedResult[35] = 12.618400;
        expectedResult[36] = 52.618400;
        expectedResult[37] = 72.618400;
        expectedResult[38] = 154.456481;
        expectedResult[39] = 173.541331;
        expectedResult[40] = -5.864095;
        expectedResult[41] = 34.135905;
        expectedResult[42] = 54.135905;
        expectedResult[43] = 111.999596;
        expectedResult[44] = 131.084446;
        expectedResult[45] = 0.156505;
        expectedResult[46] = 40.156505;
        expectedResult[47] = 60.156505;
        expectedResult[48] = 125.545945;
        expectedResult[49] = 144.630795;
        expectedResult[50] = 6.177105;
        expectedResult[51] = 46.177105;
        expectedResult[52] = 66.177105;
        expectedResult[53] = 139.092295;
        expectedResult[54] = 158.177145;
        expectedResult[55] = 14.135905;
        expectedResult[56] = 54.135905;
        expectedResult[57] = 74.135905;
        expectedResult[58] = 156.999596;
        expectedResult[59] = 176.084446;
        expectedResult[60] = 1.992535;
        expectedResult[61] = 41.992535;
        expectedResult[62] = 80.970456;
        expectedResult[63] = 120.970456;
        expectedResult[64] = 140.055307;
        expectedResult[65] = 8.013135;
        expectedResult[66] = 48.013135;
        expectedResult[67] = 94.516806;
        expectedResult[68] = 134.516806;
        expectedResult[69] = 153.601656;
        expectedResult[70] = 14.033735;
        expectedResult[71] = 54.033735;
        expectedResult[72] = 108.063156;
        expectedResult[73] = 148.063156;
        expectedResult[74] = 167.148006;
        expectedResult[75] = 21.992535;
        expectedResult[76] = 61.992535;
        expectedResult[77] = 125.970456;
        expectedResult[78] = 165.970456;
        expectedResult[79] = 185.055307;
        expectedResult[80] = 6.086678;
        expectedResult[81] = 46.086678;
        expectedResult[82] = 84.641260;
        expectedResult[83] = 124.641260;
        expectedResult[84] = 143.726110;
        expectedResult[85] = 12.107278;
        expectedResult[86] = 52.107278;
        expectedResult[87] = 98.187610;
        expectedResult[88] = 138.187610;
        expectedResult[89] = 157.272460;
        expectedResult[90] = 18.127878;
        expectedResult[91] = 58.127878;
        expectedResult[92] = 111.733960;
        expectedResult[93] = 151.733960;
        expectedResult[94] = 170.818810;
        expectedResult[95] = 26.086678;
        expectedResult[96] = 66.086678;
        expectedResult[97] = 129.641260;
        expectedResult[98] = 169.641260;
        expectedResult[99] = 188.726110;
        expectedResult[100] = 22.307031;
        expectedResult[101] = 62.290045;
        expectedResult[102] = 97.254892;
        expectedResult[103] = 137.254892;
        expectedResult[104] = 156.339742;
        expectedResult[105] = 28.327631;
        expectedResult[106] = 68.348273;
        expectedResult[107] = 110.801241;
        expectedResult[108] = 150.801241;
        expectedResult[109] = 169.886092;
        expectedResult[110] = 34.348231;
        expectedResult[111] = 74.406502;
        expectedResult[112] = 124.347591;
        expectedResult[113] = 164.347591;
        expectedResult[114] = 183.432441;
        expectedResult[115] = 42.307031;
        expectedResult[116] = 82.415045;
        expectedResult[117] = 142.254892;
        expectedResult[118] = 182.254892;
        expectedResult[119] = 201.339742;
        expectedResult[120] = 22.684575;
        expectedResult[121] = 62.654477;
        expectedResult[122] = 97.529783;
        expectedResult[123] = 137.529783;
        expectedResult[124] = 156.614634;
        expectedResult[125] = 28.705175;
        expectedResult[126] = 68.739046;
        expectedResult[127] = 111.076133;
        expectedResult[128] = 151.076133;
        expectedResult[129] = 170.160983;
        expectedResult[130] = 34.725774;
        expectedResult[131] = 74.823615;
        expectedResult[132] = 124.622483;
        expectedResult[133] = 164.622483;
        expectedResult[134] = 183.707333;
        expectedResult[135] = 42.684575;
        expectedResult[136] = 82.866977;
        expectedResult[137] = 142.529783;
        expectedResult[138] = 182.529783;
        expectedResult[139] = 201.614634;
        int count = 0;
        for (int pi = 0; pi < 7; pi++) {
            for (int fi = 0; fi < 4; fi++) {
                for (int di = 0; di < 5; di++) {
                    double result = calculator.pl_1411_lowheight(f[fi], d[di], type, p[pi], w);
                    util.assertDoubleEquals(expectedResult[count], result);
                    count = count + 1;
                }
            }
        }
    }
    @Test
    public void test7() {
        P1411 calculator = new P1411();
        double[] p = new double[7];
        double[] f = new double[4];
        double[] d = new double[5];
        double w = 20;
        p[0] = 0.100000;
        p[1] = 5.000000;
        p[2] = 10.000000;
        p[3] = 50.000000;
        p[4] = 70.000000;
        p[5] = 99.000000;
        p[6] = 99.100000;
        d[0] = 0.100000;
        d[1] = 10.000000;
        d[2] = 100.000000;
        d[3] = 1000.000000;
        d[4] = 3000.000000;
        f[0] = 0.300000;
        f[1] = 0.600000;
        f[2] = 1.200000;
        f[3] = 3.000000;
        P1411.ClutterEnvironment type = P1411.ClutterEnvironment.URBAN;
        double[] expectedResult = new double[140];
        expectedResult[0] = -10.395332;
        expectedResult[1] = 29.604668;
        expectedResult[2] = 49.604668;
        expectedResult[3] = 69.604668;
        expectedResult[4] = 125.223681;
        expectedResult[5] = -4.374732;
        expectedResult[6] = 35.625268;
        expectedResult[7] = 55.625268;
        expectedResult[8] = 75.625268;
        expectedResult[9] = 138.770030;
        expectedResult[10] = 1.645868;
        expectedResult[11] = 41.645868;
        expectedResult[12] = 61.645868;
        expectedResult[13] = 81.645868;
        expectedResult[14] = 152.316380;
        expectedResult[15] = 9.604668;
        expectedResult[16] = 49.604668;
        expectedResult[17] = 69.604668;
        expectedResult[18] = 89.604668;
        expectedResult[19] = 170.223681;
        expectedResult[20] = -7.381600;
        expectedResult[21] = 32.618400;
        expectedResult[22] = 52.618400;
        expectedResult[23] = 116.256481;
        expectedResult[24] = 135.341331;
        expectedResult[25] = -1.361000;
        expectedResult[26] = 38.639000;
        expectedResult[27] = 58.639000;
        expectedResult[28] = 129.802831;
        expectedResult[29] = 148.887681;
        expectedResult[30] = 4.659600;
        expectedResult[31] = 44.659600;
        expectedResult[32] = 64.659600;
        expectedResult[33] = 143.349181;
        expectedResult[34] = 162.434031;
        expectedResult[35] = 12.618400;
        expectedResult[36] = 52.618400;
        expectedResult[37] = 72.618400;
        expectedResult[38] = 161.256481;
        expectedResult[39] = 180.341331;
        expectedResult[40] = -5.864095;
        expectedResult[41] = 34.135905;
        expectedResult[42] = 54.135905;
        expectedResult[43] = 118.799596;
        expectedResult[44] = 137.884446;
        expectedResult[45] = 0.156505;
        expectedResult[46] = 40.156505;
        expectedResult[47] = 60.156505;
        expectedResult[48] = 132.345945;
        expectedResult[49] = 151.430795;
        expectedResult[50] = 6.177105;
        expectedResult[51] = 46.177105;
        expectedResult[52] = 66.177105;
        expectedResult[53] = 145.892295;
        expectedResult[54] = 164.977145;
        expectedResult[55] = 14.135905;
        expectedResult[56] = 54.135905;
        expectedResult[57] = 74.135905;
        expectedResult[58] = 163.799596;
        expectedResult[59] = 182.884446;
        expectedResult[60] = 1.992535;
        expectedResult[61] = 41.992535;
        expectedResult[62] = 87.770456;
        expectedResult[63] = 127.770456;
        expectedResult[64] = 146.855307;
        expectedResult[65] = 8.013135;
        expectedResult[66] = 48.013135;
        expectedResult[67] = 101.316806;
        expectedResult[68] = 141.316806;
        expectedResult[69] = 160.401656;
        expectedResult[70] = 14.033735;
        expectedResult[71] = 54.033735;
        expectedResult[72] = 114.863156;
        expectedResult[73] = 154.863156;
        expectedResult[74] = 173.948006;
        expectedResult[75] = 21.992535;
        expectedResult[76] = 61.992535;
        expectedResult[77] = 132.770456;
        expectedResult[78] = 172.770456;
        expectedResult[79] = 191.855307;
        expectedResult[80] = 6.086678;
        expectedResult[81] = 46.086678;
        expectedResult[82] = 91.441260;
        expectedResult[83] = 131.441260;
        expectedResult[84] = 150.526110;
        expectedResult[85] = 12.107278;
        expectedResult[86] = 52.107278;
        expectedResult[87] = 104.987610;
        expectedResult[88] = 144.987610;
        expectedResult[89] = 164.072460;
        expectedResult[90] = 18.127878;
        expectedResult[91] = 58.127878;
        expectedResult[92] = 118.533960;
        expectedResult[93] = 158.533960;
        expectedResult[94] = 177.618810;
        expectedResult[95] = 26.086678;
        expectedResult[96] = 66.086678;
        expectedResult[97] = 136.441260;
        expectedResult[98] = 176.441260;
        expectedResult[99] = 195.526110;
        expectedResult[100] = 22.307031;
        expectedResult[101] = 62.324045;
        expectedResult[102] = 104.054892;
        expectedResult[103] = 144.054892;
        expectedResult[104] = 163.139742;
        expectedResult[105] = 28.327631;
        expectedResult[106] = 68.382273;
        expectedResult[107] = 117.601241;
        expectedResult[108] = 157.601241;
        expectedResult[109] = 176.686092;
        expectedResult[110] = 34.348231;
        expectedResult[111] = 74.440502;
        expectedResult[112] = 131.147591;
        expectedResult[113] = 171.147591;
        expectedResult[114] = 190.232441;
        expectedResult[115] = 42.307031;
        expectedResult[116] = 82.449045;
        expectedResult[117] = 149.054892;
        expectedResult[118] = 189.054892;
        expectedResult[119] = 208.139742;
        expectedResult[120] = 22.684575;
        expectedResult[121] = 62.712277;
        expectedResult[122] = 104.329783;
        expectedResult[123] = 144.329783;
        expectedResult[124] = 163.414634;
        expectedResult[125] = 28.705175;
        expectedResult[126] = 68.796846;
        expectedResult[127] = 117.876133;
        expectedResult[128] = 157.876133;
        expectedResult[129] = 176.960983;
        expectedResult[130] = 34.725774;
        expectedResult[131] = 74.881415;
        expectedResult[132] = 131.422483;
        expectedResult[133] = 171.422483;
        expectedResult[134] = 190.507333;
        expectedResult[135] = 42.684575;
        expectedResult[136] = 82.924777;
        expectedResult[137] = 149.329783;
        expectedResult[138] = 189.329783;
        expectedResult[139] = 208.414634;
        int count = 0;
        for (int pi = 0; pi < 7; pi++) {
            for (int fi = 0; fi < 4; fi++) {
                for (int di = 0; di < 5; di++) {
                    double result = calculator.pl_1411_lowheight(f[fi], d[di], type, p[pi], w);
                    util.assertDoubleEquals(expectedResult[count], result);
                    count = count + 1;
                }
            }
        }
    }
    @Test
    public void test8() {
        P1411 calculator = new P1411();
        double[] p = new double[7];
        double[] f = new double[4];
        double[] d = new double[5];
        double w = 20;
        p[0] = 0.100000;
        p[1] = 5.000000;
        p[2] = 10.000000;
        p[3] = 50.000000;
        p[4] = 70.000000;
        p[5] = 99.000000;
        p[6] = 99.100000;
        d[0] = 0.100000;
        d[1] = 10.000000;
        d[2] = 100.000000;
        d[3] = 1000.000000;
        d[4] = 3000.000000;
        f[0] = 0.300000;
        f[1] = 0.600000;
        f[2] = 1.200000;
        f[3] = 3.000000;
        P1411.ClutterEnvironment type = P1411.ClutterEnvironment.DENSE_URBAN;
        double[] expectedResult = new double[140];
        expectedResult[0] = -10.395332;
        expectedResult[1] = 29.604668;
        expectedResult[2] = 49.604668;
        expectedResult[3] = 69.604668;
        expectedResult[4] = 120.723681;
        expectedResult[5] = -4.374732;
        expectedResult[6] = 35.625268;
        expectedResult[7] = 55.625268;
        expectedResult[8] = 75.625268;
        expectedResult[9] = 134.270030;
        expectedResult[10] = 1.645868;
        expectedResult[11] = 41.645868;
        expectedResult[12] = 61.645868;
        expectedResult[13] = 81.645868;
        expectedResult[14] = 147.816380;
        expectedResult[15] = 9.604668;
        expectedResult[16] = 49.604668;
        expectedResult[17] = 69.604668;
        expectedResult[18] = 89.604668;
        expectedResult[19] = 165.723681;
        expectedResult[20] = -7.381600;
        expectedResult[21] = 32.618400;
        expectedResult[22] = 52.618400;
        expectedResult[23] = 111.756481;
        expectedResult[24] = 130.841331;
        expectedResult[25] = -1.361000;
        expectedResult[26] = 38.639000;
        expectedResult[27] = 58.639000;
        expectedResult[28] = 125.302831;
        expectedResult[29] = 144.387681;
        expectedResult[30] = 4.659600;
        expectedResult[31] = 44.659600;
        expectedResult[32] = 64.659600;
        expectedResult[33] = 138.849181;
        expectedResult[34] = 157.934031;
        expectedResult[35] = 12.618400;
        expectedResult[36] = 52.618400;
        expectedResult[37] = 72.618400;
        expectedResult[38] = 156.756481;
        expectedResult[39] = 175.841331;
        expectedResult[40] = -5.864095;
        expectedResult[41] = 34.135905;
        expectedResult[42] = 54.135905;
        expectedResult[43] = 114.299596;
        expectedResult[44] = 133.384446;
        expectedResult[45] = 0.156505;
        expectedResult[46] = 40.156505;
        expectedResult[47] = 60.156505;
        expectedResult[48] = 127.845945;
        expectedResult[49] = 146.930795;
        expectedResult[50] = 6.177105;
        expectedResult[51] = 46.177105;
        expectedResult[52] = 66.177105;
        expectedResult[53] = 141.392295;
        expectedResult[54] = 160.477145;
        expectedResult[55] = 14.135905;
        expectedResult[56] = 54.135905;
        expectedResult[57] = 74.135905;
        expectedResult[58] = 159.299596;
        expectedResult[59] = 178.384446;
        expectedResult[60] = 1.992535;
        expectedResult[61] = 41.992535;
        expectedResult[62] = 83.270456;
        expectedResult[63] = 123.270456;
        expectedResult[64] = 142.355307;
        expectedResult[65] = 8.013135;
        expectedResult[66] = 48.013135;
        expectedResult[67] = 96.816806;
        expectedResult[68] = 136.816806;
        expectedResult[69] = 155.901656;
        expectedResult[70] = 14.033735;
        expectedResult[71] = 54.033735;
        expectedResult[72] = 110.363156;
        expectedResult[73] = 150.363156;
        expectedResult[74] = 169.448006;
        expectedResult[75] = 21.992535;
        expectedResult[76] = 61.992535;
        expectedResult[77] = 128.270456;
        expectedResult[78] = 168.270456;
        expectedResult[79] = 187.355307;
        expectedResult[80] = 6.086678;
        expectedResult[81] = 46.086678;
        expectedResult[82] = 86.941260;
        expectedResult[83] = 126.941260;
        expectedResult[84] = 146.026110;
        expectedResult[85] = 12.107278;
        expectedResult[86] = 52.107278;
        expectedResult[87] = 100.487610;
        expectedResult[88] = 140.487610;
        expectedResult[89] = 159.572460;
        expectedResult[90] = 18.127878;
        expectedResult[91] = 58.127878;
        expectedResult[92] = 114.033960;
        expectedResult[93] = 154.033960;
        expectedResult[94] = 173.118810;
        expectedResult[95] = 26.086678;
        expectedResult[96] = 66.086678;
        expectedResult[97] = 131.941260;
        expectedResult[98] = 171.941260;
        expectedResult[99] = 191.026110;
        expectedResult[100] = 22.307031;
        expectedResult[101] = 62.301545;
        expectedResult[102] = 99.554892;
        expectedResult[103] = 139.554892;
        expectedResult[104] = 158.639742;
        expectedResult[105] = 28.327631;
        expectedResult[106] = 68.359773;
        expectedResult[107] = 113.101241;
        expectedResult[108] = 153.101241;
        expectedResult[109] = 172.186092;
        expectedResult[110] = 34.348231;
        expectedResult[111] = 74.418002;
        expectedResult[112] = 126.647591;
        expectedResult[113] = 166.647591;
        expectedResult[114] = 185.732441;
        expectedResult[115] = 42.307031;
        expectedResult[116] = 82.426545;
        expectedResult[117] = 144.554892;
        expectedResult[118] = 184.554892;
        expectedResult[119] = 203.639742;
        expectedResult[120] = 22.684575;
        expectedResult[121] = 62.674027;
        expectedResult[122] = 99.829783;
        expectedResult[123] = 139.829783;
        expectedResult[124] = 158.914634;
        expectedResult[125] = 28.705175;
        expectedResult[126] = 68.758596;
        expectedResult[127] = 113.376133;
        expectedResult[128] = 153.376133;
        expectedResult[129] = 172.460983;
        expectedResult[130] = 34.725774;
        expectedResult[131] = 74.843165;
        expectedResult[132] = 126.922483;
        expectedResult[133] = 166.922483;
        expectedResult[134] = 186.007333;
        expectedResult[135] = 42.684575;
        expectedResult[136] = 82.886527;
        expectedResult[137] = 144.829783;
        expectedResult[138] = 184.829783;
        expectedResult[139] = 203.914634;
        int count = 0;
        for (int pi = 0; pi < 7; pi++) {
            for (int fi = 0; fi < 4; fi++) {
                for (int di = 0; di < 5; di++) {
                    double result = calculator.pl_1411_lowheight(f[fi], d[di], type, p[pi], w);
                    util.assertDoubleEquals(expectedResult[count], result);
                    count = count + 1;
                }
            }
        }
    }

    @Test
    public void test9() {
        P1411 calculator = new P1411();
        double[] f = new double[4];
        double[] d = new double[5];
        d[0] = 30.000000;
        d[1] = 50.000000;
        d[2] = 100.000000;
        d[3] = 150.000000;
        d[4] = 170.000000;
        f[0] = 10.000000;
        f[1] = 20.000000;
        f[2] = 38.000000;
        f[3] = 73.000000;
        P1411.ClutterEnvironment type = P1411.ClutterEnvironment.RESIDENTIAL;
        double[] expectedResult = new double[20];
        expectedResult[0] = 83.961350;
        expectedResult[1] = 90.638997;
        expectedResult[2] = 99.700000;
        expectedResult[3] = 105.000347;
        expectedResult[4] = 106.636513;
        expectedResult[5] = 90.192671;
        expectedResult[6] = 96.870318;
        expectedResult[7] = 105.931321;
        expectedResult[8] = 111.231668;
        expectedResult[9] = 112.867833;
        expectedResult[10] = 95.962870;
        expectedResult[11] = 102.640518;
        expectedResult[12] = 111.701520;
        expectedResult[13] = 117.001867;
        expectedResult[14] = 118.638033;
        expectedResult[15] = 101.832133;
        expectedResult[16] = 108.509780;
        expectedResult[17] = 117.570783;
        expectedResult[18] = 122.871130;
        expectedResult[19] = 124.507296;
        int count = 0;
        for (int fi = 0; fi < 4; fi++) {
            for (int di = 0; di < 5; di++) {
                double result = calculator.pl_1411_belowroof(f[fi], d[di], type, false);
                util.assertDoubleEquals(expectedResult[count], result);
                count = count + 1;
            }
        }
    }

}

