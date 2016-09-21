ciphertext = 'edsgickxhuklzveqzvkxwkzukcvuh';
ciphertext_numberFormat = convertToNumbers(ciphertext);
result = [];
value = [];
a_inv_all = [1, 9, 21, 15, 3, 19, 7, 23, 11, 5, 17, 25];
for a = 1 : length(a_inv_all);
    for b = 0 : 25;
        tempResult = mod(a_inv_all(a)*(ciphertext_numberFormat - b), 26);
        tempResult_stringFormat = convertToString(tempResult);
        result = [result;tempResult_stringFormat];
    end
end
result