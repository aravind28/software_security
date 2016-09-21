function RSA(N,e,d)
    k = (e*d) - 1;
    while true
        t = k/2;
        g = randi([2 N-1],1,1);
        while (mod(t,2) == 0)
            x = mod(g^t, N);
            t = t/2;
            if (x > 1 && gcd(x - 1, N) > 1)
                p = gcd(x - 1, N)
                q = N/p
                return;
            end
        end   
    end
end




