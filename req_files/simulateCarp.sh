#!/bin/bash

#Submit this script with: sbatch thefilename

#SBATCH --time=24:00:00   # walltime
#SBATCH --ntasks=30   # number of processor cores (i.e. tasks)
#SBATCH --nodes=1   # number of nodes
#SBATCH --mem-per-cpu=1GB   # memory per CPU core
#SBATCH -J "Carp"   # job name
#SBATCH --mail-user=mljohnso@caltech.edu   # email address
#SBATCH --mail-type=FAIL
#SBATCH --output="SLURM-carp-%j.out"
#SBATCH --partition=expansion

wt=gdmcss
genotype=GW
male_percent=0.5
MC=0
file_label=GG_lethal

start_time=`date +%s`

this_file_out_file=${file_label}_multiruns.out

echo genotype $id, male_percent $male_percent, MC $MC
echo file label: $file_label

for intro in 200 600 1000 2000 10000
do
    for run in $(seq 1 10)
    do
        outfile=${file_label}-i_${intro}-run_${run}.txt

        echo genotype: $id >> $this_file_out_file
        echo run: $run >> $this_file_out_file
        echo intro: $intro >> $this_file_out_file
        echo outfile: $outfile >> $this_file_out_file

        sed -i -e 's/INITIAL_GENOTYPE: .*/INITIAL_GENOTYPE: ['$wt']/g' config.txt
        sed -i -e 's/RESTOCK_GENOTYPE: .*/RESTOCK_GENOTYPE: ['$genotype']/g' config.txt
        sed -i -e 's/RESTOCK_MALE_PERCENT: .*/RESTOCK_MALE_PERCENT: ['$male_percent']/g' config.txt
        sed -i -e 's/FILE_INFO: .*/FILE_INFO: ['$file_label'-i_'$intro']/g' config.txt
        sed -i -e 's/RESTOCK_AMOUNT: .*/RESTOCK_AMOUNT: ['$intro']/g' config.txt
        sed -i -e 's/maternalCarryoverFrequency: .*/maternalCarryoverFrequency: ['$MC']/g' config.txt

        srun --ntasks 1 java -jar ../../SyntSpec_1.7.jar >> $outfile

    done
done

wait
end_time=`date +%s`
echo script time taken:  >> $this_file_out_file
echo $((end_time - start_time))  >> $this_file_out_file
