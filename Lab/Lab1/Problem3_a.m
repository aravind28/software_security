plaintext = 'GAATTCGCGGCCGCAATTAACCCTCACTAAAGGGATCT';
plaintext_numberFormat = convertNucleotideToNumber(plaintext);
plaintext_afterShift = shiftNumber(plaintext_numberFormat);
ciphertext = convertNumberToNucleotide(plaintext_afterShift)