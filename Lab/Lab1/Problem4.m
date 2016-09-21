clear;clc;
n = 718548065973745507;
e = 3449;
d = 543546506135745129;

k = (e * d) - 1;
while(mod(k, 2) == 0)
    g = randi([2 34], 1, 1)
    t = k
    while (mod(t, 2) == 0)
        t = t / 2
        x = mod((g^t), n)
    end
    x = x - 1
    y = gcd(x, n)
    if((x > 1) & (y > 1))
        p = y
        q = n/p
        return
    end
end