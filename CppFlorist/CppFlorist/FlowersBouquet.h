#pragma once
#include <string>
#include <vector>

class FlowersBouquet {
public:
	void arrange();

private:
	std::vector<std::string> bouquet;
	bool is_arranged;
};
