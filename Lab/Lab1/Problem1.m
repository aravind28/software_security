plaintext = 'ycvejqwvhqtdtwvwu';
plaintext_numberFormat = convertToNumbers(plaintext);
result = [];
for shift = 0:25;
    plaintext_tempResult = (plaintext_numberFormat - shift);
    plaintext_stringFormat = convertToString(plaintext_tempResult);
    result = [result;plaintext_stringFormat];
end
result
