l = gets.chomp
n = [0,0,0,0]
i = 0
0.upto(l.length-2) do
  c = l[i]
  if c == '0'
    n[0]+=1
    if l[i+1] == '0'
      n[2]+=1
    else
      n[3]+=1
    end  
  else
    n[1]+=1
  end
  i+=1
end

if l[l.length-1] == '0'
  n[0]+=1
  if l[0] == '0'
    n[2]+=1
  else
    n[3]+=1
  end
else
  n[1]+=1
end

num = (n[0]*1.0)/(n[0]+n[1])
after = (n[2]*1.0)/(n[2]+n[3])

if num == after
  puts "EQUAL"
elsif after > num
  puts "SHOOT"
else
  puts "ROTATE"
end
