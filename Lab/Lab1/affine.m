function affine(x)
    a = 3;
    b = 22;
    numArray = convertToNumbers(x);
    for i=1:length(x)
        numArray(i) = mod(a*numArray(i) + b,26);
    end
    charArray = convertToString(numArray)
end