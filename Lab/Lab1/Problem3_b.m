function result = Problem3_b(a, b)
    plaintext = 'GAATTCGCGGCCGCAATTAACCCTCACTAAAGGGATCT';
    plaintext_numberFormat = convertNucleotideToNumber(plaintext);
    result = mod(a*plaintext_numberFormat + b, 4);
    result = convertNumberToNucleotide(result);
end
