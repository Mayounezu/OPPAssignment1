#pragma once
#include <string>
#include <vector>#pragma once

class Person;
class DeliveryPerson;
class FlowerArranger;
class DeliveryPerson;

class Florist {
public:
	Florist(DeliveryPerson* deliveryPerson,
	FlowerArranger* flowerArranger,
	DeliveryPerson* deliveryPerson, std::string name);
	void acceptOrder(Person* recipient,
		std::vector<std::string> flowers);
private:
	DeliveryPerson* deliveryPerson;
	FlowerArranger* flowerArranger;
	DeliveryPerson* deliveryPerson;
	std::string name;
};