t = gets.to_i
1.upto(t) do
  n = gets.to_i
  l = gets.chomp
  a = [0,0,0,0,0,0,0,0]
  for i in 0..37
    o = l[i..i+2]
    if o == "TTT"
      a[0]+=1
    elsif o == "TTH"
      a[1]+=1
    elsif o == "THT"
      a[2]+=1
    elsif o == "THH"
      a[3]+=1
    elsif o == "HTT"
      a[4]+=1
    elsif o == "HTH"
      a[5]+=1
    elsif o == "HHT"
      a[6]+=1
    else
      a[7]+=1
    end
  end
  puts "#{n} #{a[0]} #{a[1]} #{a[2]} #{a[3]} #{a[4]} #{a[5]} #{a[6]} #{a[7]} "
end
