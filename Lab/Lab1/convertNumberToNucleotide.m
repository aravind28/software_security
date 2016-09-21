function nucleotideCipher= convertNumberToNucleotide(y)

for i = 1:length(y);
    if(y(i) == 0)
        alphabet = 'A';
    elseif(y(i) == 1)
        alphabet = 'C';
    elseif(y(i) == 2)
        alphabet = 'G';
    elseif(y(i) == 3)
        alphabet ='T';
    end
    nucleotideCipher(i) = alphabet;
end

