let "a = 0"
for  i in `find *`
do 
	b=`wc -l $i`
	result=`echo $b|cut -d" " -f1`
	let "a=a+$result"
done
echo $a
