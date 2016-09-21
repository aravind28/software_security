function numbersAfterShift = shiftNumber(x)

for i = 1:length(x)
    numbersAfterShift(i) = mod((x(i) + 1), 4);
end

end

