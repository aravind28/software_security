function printAllShiftCiphers(x)
for i=1:25
    shiftTextByK(x, i)
end
end

function shiftTextByK(x, k)
numArray=convertToNumbers(x);
for i=1:length(numArray)
    numArray(i)=mod(numArray(i)-k,26);
end
charArray=convertToString(numArray)
end



