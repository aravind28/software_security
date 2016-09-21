function chromosome(a,b)
    keySet = {'A', 'C', 'G', 'T'};
    valueSet = [0 1 2 3];
    mapObj = containers.Map(keySet,valueSet);
    invMapObj = containers.Map(valueSet,keySet);
    
    chromosomeStr = 'GAATTCGCGGCCGCAATTAACCCTCACTAAAGGGATCT';
    cipherNum = mapObj(chromosomeStr(1));
    for i=2:length(chromosomeStr)
        cipherNum(end + 1) = mapObj(chromosomeStr(i));
    end
    
    for i=1:length(chromosomeStr)
        cipherNum(i) = mod(a*cipherNum(i) + b, 4);
    end
    
    cipherChar = invMapObj(cipherNum(1));
    for i=2:length(chromosomeStr)
        cipherChar(end + 1) = invMapObj(cipherNum(i));
    end
    
    cipherChar
end