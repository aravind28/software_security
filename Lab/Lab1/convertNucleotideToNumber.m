function nucleotideNumber = convertNucleotideToNumber(inputNucleotide)

for element = 1:length(inputNucleotide)
    if(inputNucleotide(element) == 'A');
        nmbr = 0;
    elseif(inputNucleotide(element) == 'C');
        nmbr = 1;
    elseif(inputNucleotide(element) == 'G');
        nmbr = 2;
    elseif(inputNucleotide(element) == 'T');
        nmbr = 3;
    end
    nucleotideNumber(element) = nmbr;
end

