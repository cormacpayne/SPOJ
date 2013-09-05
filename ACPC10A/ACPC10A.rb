a,b,c = gets.strip.split(' ').map &:to_i
while a != 0 || b != 0 || c != 0 do
  if(b-a == c-b)
    num = (c+c)-b
    puts "AP #{num}"
  else
    num = (c*c)/b
    puts "GP #{num}"
  end
  a,b,c = gets.strip.split(' ').map &:to_i
end

