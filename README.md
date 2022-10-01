# Java Implementation of Recommendation ITU-R P.1411 (Site-General Prediction Methods)

This code repository contains a Java software implementation of site-general prediction methods in [Recommendation ITU-R P.1411-11](https://www.itu.int/rec/R-REC-P.1411/en) §4.1.1, §4.2.1 and §4.3.1 with propagation data and prediction methods for the planning of short-range outdoor radiocommunication systems and radio local area networks in the frequency range 300 MHz to 100 GHz.  

This version of the code is also implemented in [SEAMCAT](https://seamcat.org). 


The following table describes the structure of the folder `./src/` containing the Java implementation of Recommendation ITU-R P.1411.

| File/Folder               | Description                                                         |
|----------------------------|---------------------------------------------------------------------|
|`main/P1411.java`                | Java class implementing Recommendation ITU-R P.1411-11        |
|`test/P1411Test.java`          | Java class implementing validation tests          |



## §4.1.1: Site-general model where both the Tx and Rx stations are located below-rooftop regardless of their antenna heights
~~~ 
L = pl_1411_belowroof(f, d, env, var)
~~~

## Required input arguments of function `pl_1411_belowroof`

| Variable          | Type   | Units | Limits       | Description  |
|-------------------|--------|-------|--------------|--------------|
| `f`               | double | GHz   | See Note  | Frequency | 
| `d`               | double | m   | See Note   | 3D direct distance between Tx and Rx stations  |
| `env`      | ClutterEnvironment |    | URBAN,  HIGH_RISE_URBAN, SUBURBAN, RESIDENTIAL | Envirnonment type |
| `var`      | boolean |     |  | Set to `true` if variations are to be computed in Monte-Carlo analysis |

### Note 
 <b>URBAN:  </b> LoS, Urban (high-rise, low-rise)/Suburban
 <br>     `f` = 0.8 - 82 GHz, `d` = 5 - 660 m <br> 
 <b>HIGH_RISE_URBAN: </b> NLoS, Urban high-rise
 <br>     `f` = 0.8 - 82 GHz, `d` = 30 - 715 m
 <br> <b>SUBURBAN: </b> NLoS,  Urban low-rise/Suburban
 <br>    `f` = 10 - 73 GHz, `d` = 30 - 250 m
 <br> <b>RESIDENTIAL: </b>NLoS,  Residential
 <br>    `f` = 0.8 - 73 GHz, `d` = 30 - 170 m


## §4.2.1: Site-general model where one of the stations is located below-rooftop, and the other above-rooftop  regardless of their antenna heights
~~~ 
L = pl_1411_aboveroof(f, d, env, var)
~~~

## Required input arguments of function `pl_1411_aboveroof`

| Variable          | Type   | Units | Limits       | Description  |
|-------------------|--------|-------|--------------|--------------|
| `f`               | double | GHz   | See Note  | Frequency | 
| `d`               | double | m   | See Note   | 3D direct distance between Tx and Rx stations  |
| `env`      | ClutterEnvironment |    | URBAN,  HIGH_RISE_URBAN | Envirnonment type |
| `var`      | boolean |     |  | Set to `true` if variations are to be computed in Monte-Carlo analysis |

### Note 
 <b>URBAN:  </b> LoS, Urban high-rise, Urban low-rise/Suburban
 <br>     `f` = 2.2 - 73GHz, `d` = 55 - 1200 m <br> 
 <b>HIGH_RISE_URBAN: </b> NLoS, Urban high-rise
 <br>     `f` = 2.2 - 66.5 GHz, `d` = 260 - 1200 m
 
## §4.3.1: Site-general model for terminals of low hight from below roof-top to near street level (1.9 m < h < 3 m).
~~~ 
L = pl_1411_lowheight(f, d, env, p)
~~~

## Required input arguments of function `pl_1411_lowheight`

| Variable          | Type   | Units | Limits       | Description  |
|-------------------|--------|-------|--------------|--------------|
| `f`               | double | GHz   | 0.3 ≤ `f`≤ 3 | Frequency | 
| `d`               | double | m   | 0 < `d`≤ 1   | 3D direct distance between Tx and Rx stations  |
| `env`      | ClutterEnvironment |    | SUBURBAN,  URBAN, DENSE_URBAN | Envirnonment type |
| `p`      | double | %    | 0 < `p`< 100  | Location percentage |



## Output ##

| Variable   | Type   | Units | Description |
|------------|--------|-------|-------------|
| `L`    | double | dB    | Basic transmission loss |



## References

* [Recommendation ITU-R P.1411](https://www.itu.int/rec/R-REC-P.1411/en)

* [SEAMCAT - Spectrum Engineering Advanced Monte Carlo Analysis Tool](https://seamcat.org)
