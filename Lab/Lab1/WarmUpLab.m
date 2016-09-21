clear;
clc;
ciphertext_str = 'tcabtiqmfheqqmrmvmtmaq';
ciphertext = convertToNumbers(ciphertext_str);
a_inv = 9; 
plaintext_set = []; 
for b = 0:25;
    plaintext = mod(a_inv*(ciphertext - b),26);
    plaintext_str = convertToString(plaintext); 
    plaintext_set = [plaintext_set;plaintext_str];
end
plaintext_set;