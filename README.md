This is an edited version of the SyntSpec project, written by James Parker, and involving Siba Das, Maciej Maselko, James Parker, Przemek Bajer, and Michael J. Smanski.
It is an agent-based model, featuring overlapping generations, fish growth, and many species parameters finely tuned to Carp. Questions concerning code can be directed to mljohnso@caltech.edu.

# Table of Contents
1. [Requirements](#Requirements)
2. [How to Use](#how-to-use)
3. [Genotypes and Simulation Options](#genotypes-and-simulation-options)
4. [Adding Behaviors](#adding-behaviors)
5. [How does the code work?](#how-does-the-code-work)

## Requirements

- a functioning version of java (we recommend 22.0.2)
- SyntSpec.jar
- 50yfishDataSeining25.csv

This model was created in java, and included in this repo is a packaged jar. Therefore, the main requirements to run this model are the file "SyntSpec.jar" and a functioning version of Java. We tested this model in version 22.0.2, and therefore recommend it.

Also required are a fish age-to-length table, and a config file. We used and provide here the file "50yfishDataSeining25.csv", our recommended age-to-length table. If you do not provide a config file, the program will auto-generate a simple one for you. For an example of all the configuration parameters available, see the file "config_allPossible.txt"

## How to Use

To run the program, simply open a command-line terminal and navigate to the directory where SyntSpec.jar and the age-to-length csv are located. In the terminal, type ```java -jar SyntSpec.jar```

The program will look for a file named ```config.txt```. If no such file is provided, the program will automatically generate one as an example. Any parameter not specified will default to the values shown in the file ```config_allPossible.txt```. 

All parameters for the simulation are specified in config.txt. This includes the initial number of fish, the wildtype genotype, the number, frequency, and makeup of restocks, and all possible values. These can be seen in the file ```config_allPossible.txt```. To run multiple simulations, use ```SIMULATION_RUNS```. To vary a numerical parameter over multiple runs, you can use the following notation: ```VARIABLE_NAME: START_VALUE to STOP_VALUE step STEP```. For example, to simulate different starting populations sizes (such as 50, 100, 150, and 200), you can use the following: ```INITIAL_FISH: 50 to 201 step 50```. The program will then run multiple simulations with INITIAL_FISH set to 50, 100, 150, and 200. That said, it may be easier to run the simulation multiple times with different parameters and associated file names. Included in the "req_files" folder is a bash file, ```simulateCarp.sh```, that shows an example of how we ran the simulation multiple times, while varying parameters, on SLURM.

The simulation currently simulates two lakes, one called "Susan" and one called "Marsh". The relationship between these two lakes in the simulation is not currently understood. Currently, all fish released and counted in the simulation exist in Lake Susan.

The simulation, when run, will produce 5 files. Each will start with the date and time the simulation was run, followed by a file tag set by ```FILE_INFO```. The rest of the file will have the following names, and contain the following information

| <div style="width:90px">File name</div> | Description | Data example |
| :----- | :---------- | :------------- |
_AdultAbundance.txt | Number of fish and their density in the lake (number of carp over area) | <pre>Year,1,Lake,Marsh,Number,0 ,density, 0.0 <br>Year,1,Lake,Susan,Number,230 ,density, 2.3 <br>Year,2,Lake,Marsh,Number,0 ,density, 0.0 <br>Year,2,Lake,Susan,Number,8420 ,density, 1.49 <br>Year,3,Lake,Marsh,Number,0 ,density, 0.0 <br>Year,3,Lake,Susan,Number,7271 ,density, 24.43 </pre>
_Biomass.txt | The Biomass of carp in the lake (total fish mass* per area, in kg/ha), and whether winterkill occurred. | <pre>Year,1,Lake,Marsh,BioMass,0.0,winterkill, false <br>Year,1,Lake,Susan,BioMass,11.927869598867956,winterkill, false <br>Year,2,Lake,Marsh,BioMass,0.0,winterkill, false <br>Year,2,Lake,Susan,BioMass,10.250959284498297,winterkill, false <br>Year,3,Lake,Marsh,BioMass,0.0,winterkill, false <br>Year,3,Lake,Susan,BioMass,13.480008738002166,winterkill, false</pre>
_configs.txt | A list of all the parameters used for the simulation, as taken from the configuration file "config.txt" and from defaults | <pre>WORKER_THREAD_COUNT: 24 <br>multipleLogs: true <br>outputPostSimulationData: false <br>filterSeining: 1
_Gene_Generation.txt | For each year, the number of females and males of each age currently inhabiting the lake. Each age gets its own row. | <pre>Run,1,Year,1,Lake,Susan,Age,0,Number,45285---generation:-1=45285 (males=22647/females=22638)__ <br>Run,1,Year,1,Lake,Susan,Age,2,Number,4---generation:-1=4 (males=4/females=0)__ <br>Run,1,Year,1,Lake,Susan,Age,3,Number,2---generation:-1=2 (males=0/females=2)__ <br>Run,1,Year,1,Lake,Susan,Age,4,Number,204---generation:-1=204 (males=101/females=103)__ <br>Run,1,Year,1,Lake,Susan,Age,5,Number,4---generation:-1=4 (males=3/females=1)__ </pre>
.txt | For each year, the number of males and females of each age and of each Genotype inhabiting the lake. Each age gets its own row, and genotypes share a row | <pre>Simulation run #1 <br>Run,1,Year,1,Lake,Susan,Age,0,Number,45285---types:GW=24985 (males=12404/females=12581)\_\_WG=14123 (males=7184/females=6939)\_\_WW=6177 (males=3059/females=3118)\_\_ <br>Run,1,Year,1,Lake,Susan,Age,2,Number,4---types:WW=4 (males=4/females=0)__ <br>Run,1,Year,1,Lake,Susan,Age,3,Number,2---types:WW=2 (males=0/females=2)__ <br>Run,1,Year,1,Lake,Susan,Age,4,Number,204---types:GW=200 (males=100/females=100)\_\_WW=4 (males=1/females=3)__ <br>Run,1,Year,1,Lake,Susan,Age,5,Number,4---types:WW=4 (males=3/females=1)__ </pre>


*Mass of each fish is calculated as follows: $6 \cdot {fish} {\_} {length}^{2.763} \cdot 10^{-8} $



## Genotypes and Simulation Options

The simulation can handle a number of different drive types and genotypes within those drives. Provided here is a list of "Drive Types", their behavior, and the genotypes those individuals may have.

Stocked individuals are broadly classified into 4 types of carp, and subject to reproduction systems explained below.

1) **SSFLCarps** have a genome of length 6, are subject to SSIMS reproduction
2) **GDCarps** have a genome of length 2, are subject to SGD reproduction
3) **ASCarps** have a genome of length 5, are subject to masculinizing allele sail reproduction
4) **SSGenomeCarps** have a genome of ANY length greater than zero (excluding the above possible lengths), can be subject to trojan, FL, Allele sail with maternal carryover, or GDMCSS reproduction. 
5) **SSCarps** are all other carps. i.e., any carp with a starting genotype that is empty. They have no special mating behaviors.

If two individuals mate, the reproduction rules they will follow will be determined by their genomes, and they will undergo whichever system is higher ranked in this list. i.e., if a GDCarp and an ASCarp mate, they will observe SGD reproduction and will not have allele sail behavior. As such, *drive types should be run separately. While it is possible to stock multiple different types of carp, there are no guarantees that they will behave correctly upon interaction.* Using these structures, the following drive and suppression systems can be modeled.

**Synthetic Genetic Incompatibility (SGI):** two individuals of different genotypes are incompatible, and produce no offspring. In the simulation, wildtype individuals are considered to have the genotype "pptt" and stocked individuals are of genotype "PPTT". Any invididual that has both a "p" and a "T" will be inviable. *If stocked individuals do not meet any other simulation conditions, they default to being classified as SGI individuals*. The "p" allele may mutate into a "P" with frequency ```PromoterMutationFrequency```, and the "T" allele may mutate into "t" with frequency ```PTAMutationFrequency```.

**Self-Stocking Incompatible Male System (SSIMS):** SGI where female offspring of lab-reared individuals are non-viable. In the simulation, wildtype individuals are considered to have the genotype "ppttll" and stocked individuals are "PPTTLL". Any individual that has both a "p" and a "T" will be inviable. And female offspring that has an "L" will be inviable. *Stocked individuals with a genome of length six will be assumed to be SSIMS individuals*. The p and T alleles may mutate as above, and the "L" allele may mutate into a non-lethal "l" with frequency ```FLMutationFrequency```. 

**Female Lethal (FL):** Individuals have either wildtype alleles (l) or have a female lethal gene (L). Female offspring with an L are inviable. *We recommend performing this using the SSIMS method, with no individuals with P or T alleles. Instead, use ppttLL and ppttll*

**Suppression Gene Drive (SGD):** Individuals can have the following alleles: wildtype (W), a homozygous-lethal driving gene (G), a resistant but functional gene (R), or a non-homed but still lethal gene (L). An individual of genotype GW, when it reproduces, will have some chance of converting the W it would pass on into G, some chance that the homing will fail and convert the W to either R or L, or that it may remain a W. All other allele combinations are subject to random (50/50 chance) inheritance. If the offspring has an R or a W, it will survive. Else, it will be inviable; offspring that are GG, GL, or LL will be inviable. *Stocked individuals with a genome of length two will be assumed to be SGD individuals*.
The chance that the G allele causes cleavage set by ```homingFrequency```, and the odds that the cleavage results in non-homologous end joining (NHEJ) are set by ```NHEJfrequency```. 1/3 of the time, NHEJ results in functioning allele that is resistant to further cleavage, and 2/3 of the time it results in an allele that has simply lost its function. (Note: this ratio is found in the reproduceGD function in SSCarp.java, and can be changed if desired).

**Gene Drive and Maternal Carryover causing Sex Skew (GDMCSS):** The same as SGD, but instead of driving a lethal element, the driving allele (G) causes recessive maleness. i.e., if the offspring has a W allele, it has a 50% chance of being male. Offspring that are GG, GL, or LL, will always be male. *To specify that a stocked individual is GDMCSS, its genotype must start with the tag "gdmcss"*. For example, ```RESTOCK_GENOTYPE: [gdmcssGG]``` and ```INITIAL_GENOTYPE: [gdmcssWW]```. Currently, the GDMCSS pattern **does not work for longer genotypes**. i.e., individuals that are tetraploid "gdmcssWWGG" will only produce diploid offspring, passing on only one of the first two characters after the "gdmcss" tag. 
The chances of drive, NHEJ, and resistant allele formation are set by the same variables as in the SGD case. The chance that a "G" allele in the mother will convert an inherited "W" allele to G is set by ```maternalCarryoverFrequency```. This does not include a chance to turn the W into R or L.

**YY Trojan:** Males that are YY are viable and will produce all male offspring. Individuals that are Trojans have the label "TROJANS" in their genotype. Of note, you can release trojan (YY) females if desired. Again, *individuals are only trojan if their genotype contains the word TROJANS*. Due to the other considerations, we recommend just using "TROJANS" to identify your trojan YY individuals, and "null" to represent non-trojans.

**Masculinizing Allele Sail:** Individuals have two loci. At the first loci, individuals can have a sail (S) or have no sail (N). At the second loci, individuals can have a functioning gene required for femaleness (F) or have a cleaved, male-causing gene (M). In the germline, each sail present in an individual has a chance of changing an F that would be passed on into an M. *Individuals that have a genotype of length 5 will be assumed to be Allele Sail individuals.* We used "ANNFF" to designate wildtype individuals, and "ASSMM" for our homozygous individuals.
The chance that the sail will convert F to M is set by ```editingFrequency```.

**Masculinizing Allele Sail with Maternal Carryover:** Similar to the Masculinizing Allele Sail, individuals can have Sail/No-sail loci (S or N), and a Female/Male locus (F or M). Each sail acts within a parent to convert a germ-line F to an M. Additionally, the mother's sails may act on F alleles present in the offspring, with chance set by the variable ```maternalCarryoverFrequency```. *Individuals for this system must have genotypes starting with the tag* ***"Amc"***. With this system, it is possible to include multiple loci and still get proper reproduction. We have used "AmcSSSSSSMM" and "AmcNNNNNNFF" to represent individuals with 3 insertions of a sail. Other combinations, in any order of loci, should function fine. 
The chance that the sail will convert F to M is set by ```editingFrequency```, and the odds that a mother's sail allele(s) will convert inherited F to M is set by ```maternalCarryoverFrequency```.



## Adding Behaviors

There are already a few behaviors possible that are built into the program, but not accessible just using the configurations. To add in these behaviors or code in your own will require editing the classes themselves. Here, I'll speak a little bit on how I went about doing that, the workflow that has worked best for me, and some easy behavior changes you can add or modify yourself!

### File Editing Basics
To edit the program, you need all of the ```.java``` class files. In theory, you can edit these as text files and package them into a runnable jar through your terminal. I prefer to use the Eclipse IDE, which makes the files easier to view, edit, and adds a graphic menu that you can use to package them. The Eclipse IDE can be found and downloaded here: https://eclipseide.org/. Some notes on how I prefer to use it:

To test my code, I'll use the "Run" feature. When I want to generate results, I'll instead "Export" the project to a Runnable jar file, so I can save the commands I use in a file elsewhere.

[//]: # (Upon opening Eclipse, it will open a workspace with a blank set)

## How does the code work?

The starting piece of code, the main class to run, is ```Wrapper.java```. This looks for a config file and populates variables accordingly. Any unspecified variables have their defaults listed and taken from ```SimConfigs.java```. 

Once all parameters are specified, the Wrapper calls ```SSCarp.mainOld```. ```SSCarp.java``` is where the majority of the simulation takes place, from seining and growth to mating and making children. For some carp types, mating is specific to their class, and specified in their class files. Most work to add a behavior happens in the ```SScarp.java```. 

The simulation starts by designating a default lake, and then performs a ```restock```. This adds the fish specified by the input parameters. Then, the program will ```simulateDeath``` and ```simulateWinterSeining```. This removes individuals by chance, and by age. ```simulateFishReproduction``` is the interesting function. It starts by separating the wild and synthetic adult males, and does a preliminary mating with the females. Each potential placeholder offspring and the mating that cause dit is stored. Then, for each potential baby, the program calls ```makeBaby```, which is described more below. Surviving babies and parents then undergo ```simulateFishGrowth``` and ```simulateFishAging```. The cycle repeats each year. 

```makeBaby``` has the following logic:
- if the father is an "SSCarp", produce no child*
- if the father is an "FLCarp", produce the potential baby*
- if either parent is an "SSFLCarp", make the other parent an SSFLCarp and follow SSFL reproduction rules
- if either parent is a "GDCarp", make the other parent a GDCarp and follow GD reproduction rules
- if either parent is an "ASCarp", make the other parent an ASCarp and follow AS reproduction rules
- if either parent is an "SSGenomeCarp", make the other parent an SSGenomeCarp
- if either parent's genome contains "TROJANS", follow trojan mating rules
- if either parent's genome contains "Amc", follow allele sail rules with maternal carryover of editing
- if either parent's genome contains "gdmcss", follow gene drive sex skew mating behavior
- if none of the above, reproduce follwing "general" rules. 

To add the Allele Sail behaviors, from scratch, I had to do three things
1) ensure there's a way to stock desired individuals
2) when mating relevant individuals, ensure they are identified as such and are assigned the correct mating rules
3) write the proper mating rules

[//]: # (In the class SSCarp)
[//]: # (note that it is singular)
[//]: # (, there is the line ```static String config = "None";```. This line can be changed to ```static String config = "SS"```, in which case all individuals )