function strOut = convertToString(x)
%strOut — function converts an array of numbers into a string representation

a = uint8('a');
strOut = char(uint8(x)+a);