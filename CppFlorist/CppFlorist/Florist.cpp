#include "Florist.h"
#include <string>
#include <vector>

class Person;
class Wholesaler;
class FlowerArranger;
class DeliveryPerson;

Florist::Florist(Wholesaler* wholesaler, FlowerArranger* flowerArranger, DeliveryPerson* deliveryPerson, std::string name)
{
}

void Florist::acceptOrder(Person* recipient, std::vector<std::string> flowers)
{
}
