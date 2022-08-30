from geopy.geocoders import Nominatim
import sys

geolocator = Nominatim(user_agent="MyApp")
coordinates = sys.argv[1]

location = geolocator.reverse(coordinates)
address = location.raw['address']
city = address.get('town', '')
if not city:
    city = address.get('city', '')


if city.isalnum():
    print(city)



else:
    ascii_values = [ord(character) for character in city]
    print(*ascii_values)

