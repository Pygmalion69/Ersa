# Preservation Metrics for Cultural Heritage Collections

Understanding how environmental conditions affect the longevity of museum, archive and library collections is a complex problem.  Traditional recommendations such as *21 °C and 50 % relative humidity* fail to acknowledge the wide range of materials in collections or the fact that few institutions can maintain constant conditions.  To give preservation professionals a quantitative way to evaluate their storage and exhibition environments, the Image Permanence Institute (IPI) developed a suite of **preservation metrics**.  These indices convert temperature and relative‑humidity data into meaningful measures of chemical and mechanical risk and have become central to tools like the eClimateNotebook® platform【1】.  This document summarises the intent, methodology and limitations of the IPI metrics and provides guidelines for interpreting their values.

## Preservation Index and Time‑Weighted Preservation Index

### Fundamentals

The **Preservation Index (PI)** and the **Time‑Weighted Preservation Index (TWPI)** were first introduced in 1995 to evaluate the preservation potential of different environments【2】.  They extend earlier “isoperm” work by applying chemical‑kinetics principles to storage data.  The PI estimates the expected lifetime (in years) of a typical “preservation‑problem” object—acidic wood‑pulp paper, colour photographs, nitrate film, tapes, etc.—under a constant temperature and relative‑humidity pairing【1】.  It uses the Arrhenius equation to relate temperature to the rate of hydrolysis and chain‑scission reactions【2】.  A benchmark PI of **50 years** at 20 °C/45 % RH was chosen so that a typical unstable object expected to deteriorate in 50 years at room conditions will have PI = 50 in that environment【1】.  Higher PI values indicate slower chemical decay, and the PI for cellulose triacetate film (activation energy ~92 kJ mol⁻¹) is used as a proxy for many other organic materials【2】.

The **Time‑Weighted Preservation Index (TWPI)** incorporates the fact that real environments fluctuate.  Instead of simply averaging PI values, TWPI weights each time interval by the reciprocal of the PI value, giving greater influence to periods of higher degradation rates【2】.  Temperature data are smoothed using a 24‑hour moving average and relative humidity using a 30‑day moving average to approximate how real objects equilibrate【1】.  The result is a single TWPI value representing the cumulative preservation quality over the analysed period; higher values correspond to longer expected lifetimes.  Institutions should ideally compute TWPI over at least a full year to capture seasonal effects【2】.

### Interpretation

IPI provides guidance for interpreting TWPI values【1】:

| TWPI range | Interpretation |
|-----------:|:--------------|
| 1–45 | **Poor environment** – rapid chemical change |
| 45–75 | **OK environment** |
| 75–100 | **Good environment** |
| >100 | **Excellent environment** |

These thresholds are approximate and help prioritise improvements; moving from a TWPI of 40 to 80 roughly doubles the expected life of sensitive materials.

### Limitations

The PI and TWPI were calibrated using data on cellulose‑derivative film and assume similar activation energies for other organic materials【2】.  Activation energies for paper, silk or magnetic tape vary widely【2】.  Therefore, these indices are **indicators of risk rather than absolute predictions**【2】.  They also do not account for pollutant exposure or light damage, and they should be used alongside institutional priorities when making environmental control decisions.

## Mold Risk Factor (MRF)

The **Mold Risk Factor (MRF)** estimates the likelihood that xerophilic mold species will germinate on objects exposed to particular temperature and humidity conditions.  It is calculated by integrating the fraction of progress toward mold germination during each measurement interval.  When conditions are favourable, growth rates from a look‑up table are converted to reciprocal values (fraction of germination per day) and multiplied by the duration of the interval; these fractions accumulate over time【1】.  Periods outside the mould‑growth boundaries contribute nothing, but the running total is maintained because spores can survive unfavourable conditions and resume growth when conditions improve【1】.  Once the running sum reaches one, germination is expected and the count resets【1】.

IPI’s recommended interpretation of MRF scores is as follows【1】:

| MRF value | Interpretation |
|---------:|:--------------|
| 0 | **Ideal** – no mold growth |
| 0 – 0.5 | **Good** – low risk |
| 0.5 – 1.0 | **Some risk** – monitor conditions |
| 1.0 | **Potential for active mold** |
| >1 | **Active mold** – growth has likely occurred |

Because MRF values may rise and fall across a year, the highest MRF value is more informative than the final value【1】.  Lower MRF values indicate safer conditions; however, results are sensitive to the chosen growth model and assume dark, still air【1】.

## Equilibrium Moisture Content Metrics

### Minimum and Maximum Equilibrium Moisture Content (MinEMC & MaxEMC)

Physical damage can occur when organic materials absorb too much or too little moisture.  The **Minimum Equilibrium Moisture Content (MinEMC)** and **Maximum Equilibrium Moisture Content (MaxEMC)** metrics use United States Forest Products Laboratory equations to convert the lagged temperature and relative‑humidity values (24‑hour average for temperature, 30‑day average for RH) into equilibrium moisture content percentages【1】.  Values are sorted to find the minimum and maximum for the monitored period, providing a sense of the driest and wettest equilibrium conditions the collection experiences【1】.  MinEMC reflects the risk of mechanical damage (cracking, warping) to hygroscopic objects, while MaxEMC has also been used to gauge risk of metal corrosion【1】.

IPI’s guidelines for the combined MinEMC/MaxEMC index are【1】:

| Condition | Interpretation |
|-----------|---------------|
| **MinEMC > 5 % and MaxEMC < 12.5 %** | **Good** – moisture levels generally safe |
| **MinEMC < 5 % or MaxEMC > 12.5 %** | **Risk** – potential for physical or mechanical damage |

Low MinEMC values (<5 %) suggest the environment is excessively dry, while high MaxEMC values (>12.5 %) indicate risk from excessive moisture or corrosion.

### Maximum Equilibrium Moisture Content (MaxEMC) for Metals

Metal objects are susceptible to corrosion when moisture levels are high.  Because modelling the combined effects of temperature and humidity on corrosion is complex, IPI uses the MaxEMC as a proxy【1】.  They recommend the following thresholds for interpreting MaxEMC【1】:

| MaxEMC | Interpretation |
|-------:|:--------------|
| **< 7 %** | **Good** – low corrosion risk |
| **7 – 10.5 %** | **OK** – monitor conditions |
| **> 10.5 %** | **Risk** – higher likelihood of corrosion |

Because MaxEMC is derived from wood‑based equations, it should be viewed as a relative indicator for metals rather than a precise measure of corrosion rate.

## Percent Dimensional Change (%DC)

The **Percent Dimensional Change (%DC)** metric estimates how much an average piece of organic material (e.g., wood) expands or contracts in response to changes in equilibrium moisture content.  Temperature and relative‑humidity data are again lagged (24 h and 30 day moving averages) and used to compute %EMC values.  The %DC is calculated as a linear function of the difference between the maximum and minimum %EMC values over the monitoring period【1】.  Because the actual starting dimensions of objects are unknown, only the relative change matters; thus the minimum %DC is subtracted from the maximum【1】.

IPI categorises %DC values as【1】:

| %DC value | Interpretation |
|---------:|:--------------|
| **< 0.5 %** | **Good** – very little dimensional change |
| **< 1.5 %** | **OK** – moderate change |
| **> 1.5 %** | **Risk** – potential mechanical damage |

Lower %DC values indicate stable moisture conditions, while higher values suggest the environment is causing objects to swell and shrink, which can lead to cracking, warping or joint failure.

### Limitations

Both the equilibrium moisture content and dimensional change metrics are derived from data on bulk wood【2】.  When applied to other hygroscopic materials (textiles, paper, leather), they serve as **approximations of risk rather than exact predictions**【2】.  They do not account for the rate of change or material‑specific mechanical properties【2】.  Consequently, collections professionals should interpret these metrics alongside material knowledge and tolerance for risk.

## Practical Guidance for Using Preservation Metrics

1. **Collect reliable environmental data.** Place calibrated temperature and RH loggers near collections and record data at regular intervals.  The metrics assume continuous records; gaps or faulty readings can skew results.

2. **Apply smoothing appropriately.** Compute 24‑hour running averages for temperature and 30‑day running averages for RH before calculating indices to approximate how materials equilibrate【1】.

3. **Interpret trends, not single numbers.** Use TWPI and MRF to compare different rooms or time periods.  A TWPI of 100 does not guarantee an object will last a century; rather, it indicates the environment has twice the preservation potential of one with TWPI = 50.

4. **Combine chemical and mechanical metrics.** Evaluate PI/TWPI alongside MinEMC/MaxEMC and %DC.  An environment may have a high TWPI (slow chemical decay) but poor MinEMC (excessive dryness) that risks warping.

5. **Consider material‑specific tolerances.** Activation energies and hygroscopic properties vary across materials【2】.  Use the metrics as guides and adjust set points based on the most sensitive objects in the collection.

6. **Document assumptions.** Record the time period analysed, smoothing windows, and any deviations from standard calculations.  This transparency aids in comparing results over time or between institutions.

## Conclusion

The IPI preservation metrics translate complex temperature and humidity data into accessible measures of chemical degradation, mold risk and mechanical stress.  The PI and TWPI use Arrhenius‑based models to estimate how environments influence the life expectancy of organic objects【2】.  MRF highlights periods conducive to mold germination and emphasises the importance of limiting high humidity【1】.  MinEMC/MaxEMC and %DC help identify risks from excessively dry or wet conditions and large moisture swings【1】.  By using these metrics together and understanding their assumptions and limitations, preservation professionals can make informed, evidence‑based decisions that balance collection care with energy and sustainability considerations.

## References
1. [Understanding Preservation Metrics](https://s3.cad.rit.edu/ipi-assets/publications/understanding_preservation_metrics.pdf)
2. [Context, Development, and Intent: An Introduction to the IPI Preservation Metrics](https://www.rit.edu/ipi/sites/rit.edu.ipi/files/documents/an_introduction_to_the_ipi_preservation_metrics.pdf)
