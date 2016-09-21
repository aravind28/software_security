function numArray = convertToNumbers(s)
%convertToNumbers - function converts a string into an array of integers in the range [0,25]

a = uint8('a');
s = lower(s);
for i=1:length(s)
    t = uint8(s(i));
    if t < a
        numArray(i) = -1;
    else
        numArray(i) = double(t - a);
    end
end
%numArray = uint8(s) - a;
numArray = double(numArray);