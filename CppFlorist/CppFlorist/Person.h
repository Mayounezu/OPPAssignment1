#pragma once
#include <string>
#include <vector>

class Florist;
class FlowersBouquet;

class Person{
public:

	void orderFlowers(Florist* florist, Person* recipient,
		std::vector<std::string> flowers);
	void acceptFlowers(FlowersBouquet* bouquet);
private:
	std::string name;

};