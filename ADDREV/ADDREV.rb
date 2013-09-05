t = gets.to_i
1.upto(t) do
  a,b = gets.split(' ')
  puts (a.reverse.to_i + b.reverse.to_i).to_s.reverse.to_i
end
