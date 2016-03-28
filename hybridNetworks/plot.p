set terminal pngcairo
set xlabel "Demanded Request"
set ylabel "Served Requests"


fullPathList = system("dir /b /s /A-D output")

do for [file in fullPathList] {
	q=system("dir /b ".file)
	q=q[:strlen(q)-4]
	print q
	set output sprintf("output/Plots/%s.png",q)
    set title sprintf("%s", q)
    plot file
}
